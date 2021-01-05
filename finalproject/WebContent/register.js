   function finish(){
        var FormObj = document.getElementById("productForm");
        var isSubmitForm = true;
        //名称
        var name = document.getElementById("proName").value;
        if (name == "") {
            document.getElementById("errProName").innerHTML = "不能为空";
            isSubmitForm = false;
        } else {
            document.getElementById("errProName").innerHTML = "";
        }
        //单价
        var price = document.getElementById("price").value;
        if (!/^\d+(\.\d+)?$/.test(price)) {
            document.getElementById("errPrice").innerHTML = "必须是数字（小数）";
            isSubmitForm = false;  
        } else {
            document.getElementById("errPrice").innerHTML = "";
        }
        //库存
        var total = document.getElementById("total").value;
        if (!/^[1-9]\d*$/.test(total)) {
            document.getElementById("errTotal").innerHTML = "必须是整数";
            isSubmitForm = false;  
        } else {
            document.getElementById("errTotal").innerHTML = "";
        }
        if (isSubmitForm) {
            FormObj.submit();
        }      
    }