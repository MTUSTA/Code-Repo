#!/bin/bash

FILES_DIR=$HOME/openvpn-clients/files
BASE_DIR=$HOME/openvpn-clients/base
CONFIGS_DIR=$HOME/openvpn-clients/configs

BASE_CONF=${BASE_DIR}/client.conf
CA_FILE=${BASE_DIR}/ca.crt
TA_FILE=${BASE_DIR}/ta.key

CLIENT_CERT=${FILES_DIR}/${1}.crt
CLIENT_KEY=${FILES_DIR}/${1}.key

# Test for files
for i in "$BASE_CONF" "$CA_FILE" "$TA_FILE" "$CLIENT_CERT" "$CLIENT_KEY"; do
    if [[ ! -f $i ]]; then
        echo " The file $i does not exist"
        exit 1
    fi

    if [[ ! -r $i ]]; then
        echo " The file $i is not readable."
        exit 1
    fi
done

# Generate client config
cat > ${CONFIGS_DIR}/${1}.ovpn <<EOF
$(cat ${BASE_CONF})
<key>
$(cat ${CLIENT_KEY})
</key>
<cert>
$(cat ${CLIENT_CERT})
</cert>
<ca>
$(cat ${CA_FILE})
</ca>
<tls-auth>
$(cat ${TA_FILE})
</tls-auth>
EOF

