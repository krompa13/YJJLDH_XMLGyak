<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:kl="autokYJJLDH.xml">
    <xsl:template match="/" >
    <html>
        <body>
            <xsl:element name="eredmeny">
                <xsl:for-each-group select="//auto" group-by="tulaj/varos/text()">
                    <xsl:element name="varos">
                        <p><xsl:element name="nev">
                              <p><xsl:value-of select="current-grouping-key()"/></p></xsl:element></p>
                        <p><xsl:element name="db">
                            <p>db: <xsl:value-of select="count( current-group())"/></p></xsl:element></p>
                        <p><xsl:element name="osszar">
                            <p>összár: <xsl:value-of select="sum( current-group()/ar)"/></p></xsl:element>
                            <p>--------------------------------</p>  </p>          
                    </xsl:element>
                </xsl:for-each-group>
            </xsl:element>
        </body>
    </html>
    </xsl:template>
</xsl:stylesheet>