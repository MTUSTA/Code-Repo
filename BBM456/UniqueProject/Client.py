import socket
import getpass
from getmac import get_mac_address as gma
import os
import time
# rsa cipher
from Crypto.PublicKey import RSA
# RSA cipher
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import pkcs1_15
from Crypto.Hash import SHA256
import binascii


class Client:

    def __init__(self, HOST, PORT, username=None, seri_num=None, publickey=None, signature=None):

        if seri_num is None:
            raise Exception('No Serial Number')

        if publickey is None:
            if os.path.exists('public.pem'):
                self.public_key = RSA.import_key(open('public.pem', 'rb').read())
            else:
                raise Exception('No Public Key')
        else:
            self.public_key = publickey

        if username is None:
            self.username = getpass.getuser()
        # elif username is not None
        else:
            self.username = username

        self.serial_number = seri_num
        self.mac_adress = gma().upper()
        self.disk_serial_number = os.popen("wmic diskdrive get serialnumber").read().split()[1][:-1]
        # self.motherboard_id = os.popen("wmic baseboard get serialnumber").read().split()[1]
        self.motherboard_id = "Standart"

        if signature is None:
            if os.path.exists('license.mtu'):
                self.signature = open('license.mtu', 'rb').read()
            else:
                self.signature = ""
        else:
            self.signature = signature

        # the client uses any non-reserved port >1024 to connect to the server and this is totally random
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client.connect((HOST, PORT))

    def collect_identities(self):
        """
        :return: all combined user info(data)
        """
        all_combined_info = self.username + "$" + self.serial_number + "$" + self.mac_adress + "$" \
                            + self.disk_serial_number + "$" + self.motherboard_id

        return all_combined_info

    def encryption(self, identities=None, public_key=None):

        if public_key is None:
            public_key = self.public_key
        if identities is None:
            identities = self.collect_identities()

        print("Client -- Raw Licence Text:", identities)
        encryptor = PKCS1_OAEP.new(public_key)
        # string to bytes
        bytes_data = identities.encode()
        encrypted = encryptor.encrypt(bytes_data)

        # convert binary to ascii
        cipher_text = binascii.hexlify(encrypted)
        print("Client -- Encrypted Licence Text:", encrypted)
        print("Client -- Encrypted Licence Text binascii.hexlify:", cipher_text)

        return encrypted


    def send_data(self, data=None):

        if data is None:
            raise Exception('None Data')
        else:
            if type(data) == bytes:
                self.client.sendall(data)
                time.sleep(3)
                self.client.sendall("okey".encode())
            else:
                raise Exception

    def accept_data(self):
        from_server = b''
        while True:
            data = self.client.recv(2048)
            if not data:
                break
            from_server += data
            self.client.send('Client'.encode())

        # convert binary to ascii
        signature = binascii.hexlify(from_server)

        print("Client -- Signature Licence Text:", from_server)
        print("Client -- Signature Licence Text binascii.hexlify:", signature)
        self.signature = from_server

    def verify(self, hashed_text=None, publickey=None, signature=None):
        if hashed_text is None:
            hashed_text = SHA256.new(self.collect_identities().encode())
        if publickey is None:
            publickey = self.public_key
        if signature is None:
            signature = self.signature

        try:
            pkcs1_15.new(publickey).verify(hashed_text, signature)
            print("The signature is valid.")
            return True
        except (ValueError, TypeError):
            print("The signature is not valid.")
            return False

    def check_license_file(self, hashed_text=None, publickey=None, signature=None):
        return self.verify(hashed_text=hashed_text, publickey=publickey, signature=signature)

    def write_signature(self, signature=None):

        if signature is None:
            signature = self.signature

        if type(signature) == str:
            signature = signature.encode()

        f = open("license.mtu", "wb")
        f.write(signature)
        f.close()

    def process(self, hashed_text=None, publickey=None, signature=None):
        cipher_text = self.encryption()
        print("Server is being requested...")
        try:
            self.send_data(cipher_text)
            # self.send_data(self.collect_identities().encode())
            time.sleep(3)
            self.accept_data()
            if self.check_license_file(hashed_text=hashed_text, publickey=publickey, signature=signature):
                self.write_signature()
                print("Client -- Succeed. The License file content is secured and signed by the server ")
            else:
                print("Client -- verification failed")
        except ConnectionRefusedError:
            print('Bağlanti Kurulamadi')


def main():
    try:
        HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
        PORT = 8080  # Port to listen on (non-privileged ports are > 1023)
        object1 = Client(HOST=HOST, PORT=PORT, seri_num="FCBB-M3AU-GLL4-99EP-RLFT")
        # if file exist
        if object1.signature != "":
            # check license file
            if object1.check_license_file():
                print("Succeed. The license is correct.")
            else:
                print("The license file has been broken!")
                # re-execute the licensing process again to obtain a valid digital signature
                object1.process()
        else:
            print("Client started...")
            print("My Mac:", object1.mac_adress)
            print("My DiskSerial:", object1.disk_serial_number)
            print("My MotherBoard ID:", object1.motherboard_id)
            print("LicenceManager service started...")
            object1.process()
    except ConnectionRefusedError:
        print("Connection could not be established")


main()
