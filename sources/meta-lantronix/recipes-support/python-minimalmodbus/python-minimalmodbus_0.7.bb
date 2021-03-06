SUMMARY = "Easy-to-use Modbus RTU and Modbus ASCII implementation for Python"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=27da4ba4e954f7f4ba8d1e08a2c756c4"

DEPENDS = "python"
RDEPENDS_${PN} = "python-pyserial"

PR = "r0"

SRC_URI = "file://MinimalModbus-${PV}.tar.gz"

SRC_URI[md5sum] = "1b2ec44e9537e14dcb8a238ea3eda451"
SRC_URI[sha256sum] = "d9acf6457bc26d3c784caa5d7589303afe95e980ceff860ec2a4051038bc261e"

S = "${WORKDIR}/MinimalModbus-${PV}"

do_install_append() {
    cp -r ${D}/usr/lib/python2.7/site-packages/MinimalModbus-${PV}-py2.7.egg/minimalmodbus.py* ${D}/usr/lib/python2.7/site-packages/
}

FILES_${PN} += "minimalmodbus.py"

inherit distutils
