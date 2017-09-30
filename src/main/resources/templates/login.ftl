<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <style>
        form button{
            background: #F9F9F9; /* Set button background */
            border: 1px solid #DFDFDF; /* Small border around our submit button */
            padding: 8px; /* Add some more space around our button text */
        }

        input{
            font: normal 12px Georgia; /* Set font for our input fields */
            border: 1px solid #DFDFDF; /* Small border around our input field */
            padding: 8px; /* Add some more space around our text */
        }
    </style>
</head>
<body>
    <div>
        <form action="/login.do" method="post">
            <span>账号：</span><input name="account" type="text" required>
            <br>
            <span>密码：</span><input name="password" type="password" required>
            <br>
            <div>
            <img src="/verificationCode?wordType=character" onclick="changeImage(this)"/>
            <input name="verificationCode" type="text" maxlength="4" required style="vertical-align: top;width: 35px;">
            </div>
            <br>
            <button type="submit">提交</button>&nbsp;<span style="color: red">${MSG!""}</span>
        </form>
    </div>
</body>
<script>
    function changeImage(obj) {
        obj.src='/verificationCode?wordType=character&'+ new Date().getTime();
    }
</script>
</html>