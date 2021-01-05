   function add(){
        var addFormObj = document.getElementById("addEmp");
        var isSubmitForm = true;
        //ID
        var id = document.getElementById("ID").value;
        if ( !/^[0-9]*$/.test(id)  || id =="") {
            document.getElementById("errID").innerHTML = "只能是数字";
            isSubmitForm = false;
        } else {
            document.getElementById("errID").innerHTML = "";
        }
        //密码
        var password = document.getElementById("password").value;
        if ( !/^[0-9]*$/.test(password)  || password =="") {
            document.getElementById("errPassword").innerHTML = "只能是数字";
            isSubmitForm = false;
        } else {
            document.getElementById("errPassword").innerHTML = "";
        }
        //邮箱
        var email = document.getElementById("email").value;
        if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)) {
            document.getElementById("msg").innerHTML = "必须是邮箱格式";
            isSubmitForm = false;  
        } else {
            document.getElementById("msg").innerHTML = "";
        } 
        //职位
        var role = document.getElementById("role").value;
        if (role == "0") {
            document.getElementById("errorRole").innerHTML = "必须选择类型";
            isSubmitForm = false;  
        } else {
            document.getElementById("errorRole").innerHTML = "";
        } 
        if (isSubmitForm) {
            addFormObj.submit();
        }
        
        
    }