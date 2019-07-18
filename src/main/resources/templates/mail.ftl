<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>邮箱测试</title>
    <style type="text/css">
        table{border-collapse: collapse;margin: 0 auto;ext-align: center;}
        table td, table th{border: 1px solid #cad9ea;color: #666; height: 30px; }
        table thead th{background-color: #CCE8EB;width: 100px;}
        table tr:nth-child(odd){background: #fff;}
        table tr:nth-child(even) {background: #F5FAFA;}
    </style>
</head>
<body>
<p>这是邮箱测试   时间： ${date}</p>
<table width="90%" class="table">
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <#list userList as user>
    <tr>
        <td>${user.name}</td>
        <td>${user.age}</td>
        <td>${user.sex}</td>
    </tr>
    </#list>
</table>
<div style="color: #FF2785">666666666666</div>
</body>
</html>