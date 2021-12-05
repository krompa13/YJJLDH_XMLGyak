<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:kl="autokYJJLDH.xml">
    version="1.0">
    <xsl:template match="/"  >
        <html>
            <body>
                    <xsl:for-each select="//auto[tulaj/varos/text()='Miskolc']">
                    <p><xsl:value-of select="@rsz"/> </p>
                    </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>