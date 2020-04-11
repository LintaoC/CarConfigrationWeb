<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.UserSelectedBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userSelected" scope="page" class="bean.UserSelectedBean"/>
<% boolean state = userSelected.handle(request); %>
<html>
<head>
    <title>Car Configuration System</title>
    <!-- The latest version of the Bootstrap core CSS file -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- The latest Bootstrap core JavaScript files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<% if (!state) { %>
<h3>Automobile null or parse error!</h3>
<% } else { %>
<div class="col-md-6 col-md-offset-3">
    <h4>Here is what you selected:</h4>
    <table class="table table-bordered table-hover">
        <tr>
            <td><% out.print(userSelected.getName()); %></td>
            <td>base price</td>
            <td><% out.print(userSelected.getBasePrice()); %></td>
        </tr>
        <% ArrayList<UserSelectedBean.ChoiceDTO> choiceDTOS = userSelected.getChoicesInf(); %>
        <% for (UserSelectedBean.ChoiceDTO choiceDTO : choiceDTOS) { %>
        <tr>
            <td><% out.print(choiceDTO.getOpSetName()); %></td>
            <td><% out.print(choiceDTO.getOpChoiceName()); %></td>
            <td><% out.print(choiceDTO.getOpChoicePrice()); %></td>
        </tr>
        <% } %>
        <tr>
            <td><b>Total Cost</b></td>
            <td></td>
            <td><b><% out.print("$" + userSelected.getTotalPrice()); %></b></td>
        </tr>
    </table>
    <% } %>
</div>
</body>
</html>
