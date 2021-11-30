<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Student Information</h2>
				<table border="1">
					<tr bgcolor="blue">
						<th>id</th>
						<th>vezeteknev</th>
						<th>keresztnev</th>
						<th>becenev</th>
                        <th>kor</th>			
		            </tr>
					<xsl:for-each select="class/student">
						<tr>
							<td><xsl:value-of select="@id"/> </td>
							<td><xsl:value-of select="vezeteknev"/></td>
							<td><xsl:value-of select="keresztnev"/></td>
							<td><xsl:value-of select="becenev"/></td>
                            <td><xsl:value-of select="kor"/></td>
                            <td><xsl:value-of select="fizetes"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>