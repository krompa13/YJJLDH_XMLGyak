<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:kl="autokYJJLDH.xml">
  <xsl:template match="/" >
  <html>
      <body>
          <xsl:element name="eredmeny">
            <xsl:for-each-group select="//auto" group-by="tipus/text()">
               <xsl:sort select="count( current-group())"/>
               <xsl:element name="tipus">
                  <p><xsl:element name="tip"><xsl:value-of select="current-grouping-key()"/></xsl:element></p>
                  <p><xsl:element name="db"><xsl:value-of select="count( current-group())"/></xsl:element></p>
               </xsl:element>
            </xsl:for-each-group>
         </xsl:element>
      </body>
  </html>
  </xsl:template>
</xsl:stylesheet>