<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Configuration System</title>
    <script type="text/javascript" src="https://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- The latest version of the Bootstrap core CSS file -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- The latest Bootstrap core JavaScript files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/static/js/configureCar.js"></script>
</head>
<body>
<div class="col-md-6 col-md-offset-3">
    <h1>Basic Car Choice</h1>
    <table class="table table-bordered table-hover">
        <tr>
            <td class="text-center"><b>Make:</b></td>
            <td>
                <select class="form-control" id="select-make">
                </select>
            </td>
        </tr>
        <tr>
            <td class="text-center"><b>Model:</b></td>
            <td class="text-center">
                <select class="form-control" id="select-model">
                </select>
            </td>
        </tr>
    </table>
    <div id="choice-tabel">
    </div>
    <button class="btn btn-default col-md-2 col-md-offset-10" type="button" id="done-button">Done</button>
</div>
</body>
</html>
