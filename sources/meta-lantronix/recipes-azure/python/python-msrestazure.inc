DESCRIPTION = "AutoRest swagger generator Python client runtime."
AUTHOR = "Microsoft Corporation"
HOMEPAGE = "https://github.com/Azure/msrestazure-for-python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=9714a4db9af8ac45d4777b479c5401ca"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-msrest \
    ${PYTHON_PN}-keyring \
    ${PYTHON_PN}-adal \
"

PR = "r0"

SRC_NAME = "msrestazure-for-python"

SRC_URI = "git://github.com/Azure/${SRC_NAME}.git"
SRCREV = "bfb9f4c7ee89eb47d4d7f2502a220b8d7e00c7b0"

S = "${WORKDIR}/git"

do_compile_prepend() {
    # Fix incorrect operators
    if [ -e setup.py ]; then
        sed -i -- 's/~=/>=/g' setup.py
    fi
}

BBCLASSEXTEND = "native nativesdk"
