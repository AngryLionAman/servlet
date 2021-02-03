<%-- 
    Document   : uploadImage
    Created on : 8 Feb, 2020, 12:16:05 PM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head><title>Image Upload</title></head>

    <body>
        <form action="UploadImage" method="post" enctype="multipart/form-data" name="productForm" id="productForm">
            <table width="400px" align="center" border=0 
                   style="background-color:yellowgreen">
                <tr>
                    <td align="center" colspan=2 style="
                        font-weight:bold;font-size:20pt;">
                        Image Details</td>
                </tr>

                <tr>
                    <td align="center" colspan=2>&nbsp;</td>
                </tr>

                <tr>
                    <td>Image Link: </td>
                    <td>
                        <input type="file" name="file" id="file">
                    <td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit" name="Submit" value="Submit"></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>

            </table>
        </form>
    </body>

</html>