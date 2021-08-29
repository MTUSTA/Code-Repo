from Crypto.PublicKey import RSA
# the argument indicates output format. There are three options:
# 'DER' - binary encoding,
# 'PEM' - texture encoding,
# 'OpenSSH' - texture encoding according to OpenSSH spec.

key = RSA.generate(1024)

f = open("private.pem", "wb")
# PEM for export is suitable for private keys
f.write(key.exportKey('PEM'))
f.close()

pubkey = key.publickey()
f = open("public.pem", "wb")
# OpenSSH for export is "only suitable for public keys
f.write(pubkey.exportKey('OpenSSH'))
f.close()
