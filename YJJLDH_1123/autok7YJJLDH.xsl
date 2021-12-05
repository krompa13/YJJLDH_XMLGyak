<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match="/"  >
   <html>
       <body>
           <p><xsl:element name="eredmeny">
           <xsl:for-each select="//auto">
               <xsl:sort select="ar"/>
                <p><xsl:element name="auto">
                    <xsl:element name="rsz"><xsl:value-of select="@rsz"/></xsl:element> <xsl:copy-of select="ar"/>
                </xsl:element></p>
           </xsl:for-each>
           </xsl:element></p> 
       </body>
   </html>
  </xsl:template>
</xsl:stylesheet>