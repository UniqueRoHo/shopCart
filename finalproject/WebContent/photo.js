
      var file = document.getElementById("pic");
      var head = document.getElementById("head");
      //一旦选中文件 发生改变  则立即读取出来
      file.onchange = function() {
          //定义图像加载对象
          var reader = new FileReader(); 
          //读取图片
          reader.readAsDataURL(file.files[0]);
          //当图片加载完毕之后，自动触发
          reader.onload = function(e) {
              //得到图片的路径 result
              var result = this.result;
              head.src = result;
          }
      }