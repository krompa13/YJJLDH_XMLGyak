<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:kl="autokYJJLDH.xml">
    version="1.0">
    <xsl:template match="/"  >
        <html>
            <body>
                <p>
                    Ennyi elemből áll: <xsl:value-of  select="count(//*)"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>