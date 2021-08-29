import socket

# rsa cipher
from Crypto.PublicKey import RSA
from Crypto.Signature import pkcs1_15
from Crypto.Hash import SHA256
from Crypto.Cipher import PKCS1_OAEP


class Server:
    def __init__(self, HOST, PORT, privatekey=None):
        if privatekey is None:
            self.private_key = RSA.import_key(open('private.pem', 'rb').read())
        else:
            self.private_key = privatekey
        self.data = None

        try:
            self.serv = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            # bind() is used to associate the socket with a specific network interface and port number:
            self.serv.bind((HOST, PORT))
            self.serv.listen(5)
            print("Socket successfully created. Waiting Client...")
        except socket.error as err:
            print("socket creation failed with error %s" % err)

    def signature(self, value):
        # SHA256 HASH
        h = SHA256.new(value)
        # loading private key in chipher
        signer = pkcs1_15.new(self.private_key)
        # sign hash
        signature = signer.sign(h)
        return signature

    def decryption(self):
        self.data.pop(-1)
        concat_data = b''.join(self.data)
        print(concat_data)
        cipher_rsa = PKCS1_OAEP.new(self.private_key)
        plain_text = cipher_rsa.decrypt(concat_data)
        print(plain_text)
        return plain_text

    def accept_data(self):

        # conn is a new socket object usable to send and receive data on the connection and
        # address is the address bound to the socket on the other end of the connection.
        self.conn, self.addr = self.serv.accept()

        from_client_list = []
        while True:
            data = self.conn.recv(2048)
            if not data:
                break
            elif data[-4:] == "okey".encode():
                data = data[:-4]
                from_client_list.append(data)
                break
            from_client_list.append(data)

        self.data = from_client_list
        print(self.data)

    def send_data(self, data):
        self.conn.sendall(data)
        self.conn.close()


def main():
    HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
    PORT = 8080  # Port to listen on (non-privileged ports are > 1023)
    server = Server(HOST, PORT)
    server.accept_data()
    plain_text = server.decryption()
    signature = server.signature(plain_text)
    server.send_data(signature)

main()
