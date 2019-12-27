DESCRIPTION = "EtherLAB master tool"
SECTION = "console/utils"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
LICENSE = "GPLv2"

# this will build head of branch master instead of a specific commit
SRCREV="${AUTOREV}"

SRC_URI = "file://ethercat-1.5.1.tar.bz2 \
           file://ec_generic.ko \
           file://ec_master.ko"
           


S = "${WORKDIR}/ethercat-1.5.1"
PR = "r1"

FILES_${PN} = "\
        /etc/init.d/ethercat \
        /etc/sysconfig/ethercat \
        ${libdir}/libethercat.so.1.0.0 \
        ${libdir}/libethercat.so.1.0 \
        ${libdir}/libethercat.so.1 \
        ${bindir}/ethercat \
        /lib/modules/3.10.0/ethercat/devices/ec_generic.ko \
        /lib/modules/3.10.0/ethercat/master/ec_master.ko"

do_configure() {
    ./configure --build=${BUILD_SYS} --host=${TARGET_SYS} --target=${TARGET_SYS} --prefix=/usr --exec_prefix=/usr --bindir=/usr/bin --sbindir=/usr/sbin --disable-8139too --disable-e100 --disable-e1000 --disable-e1000e --disable-r8169
}

do_compile() {
               make CC="${CC}"
}

do_install() {

                install -d ${D}${bindir}/
                install -d ${D}${libdir}/
                install -d ${D}/etc/init.d/
                install -d ${D}/lib/modules/3.10.0/ethercat/devices/ec_generic.ko
                install -d ${D}/lib/modules/3.10.0/ethercat/master/ec_master.ko
                install -m 0755 ${S}/lib/.libs/libethercat.so.1.0.0 ${D}${libdir}/libethercat.so.1.0.0
                install -m 0755 ${S}/tool/ethercat ${D}${bindir}/ethercat
                install -m 0755 ${S}/script/init.d/ethercat.in ${D}/etc/init.d/ethercat
                install -m 0755 ${S}/../ec_master.ko ${D}/lib/modules/3.10.0/ethercat/master/ec_master.ko
                install -m 0755 ${S}/../ec_generic.ko ${D}/lib/modules/3.10.0/ethercat/devices/ec_generic.ko

}

do_install_append() {
                ln -s libethercat.so.1.0.0 ${D}${libdir}/libethercat.so.1.0
                ln -s libethercat.so.1.0 ${D}${libdir}/libethercat.so.1
}
