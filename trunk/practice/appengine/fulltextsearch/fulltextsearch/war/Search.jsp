<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<html>
<head>
	Test App for Full Text Search.
</head>
<body>
<form name="frm" method="get" action="ftsServlet">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="22%">&nbsp;</td>
    <td width="78%">&nbsp;</td>
    </tr>
  <tr>
    <td>Document Text</td>
    <td><input type="text" name="documentText"></td>
  </tr>
    <tr>
    <td>Search String</td>
    <td><input type="text" name="searchString"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="submit" name="submit" value="Submit"></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>
</form>
</body>
</html>