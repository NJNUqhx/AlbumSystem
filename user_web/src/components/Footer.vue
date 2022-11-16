<template>
    <div class="page-icon">
        <span class="page-disabled"><i></i>上一页</span>
        <span class="page-current">1</span>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">7</a>
        <a class="page-next" href="#">下一页<i></i></a>
    </div>
</template>
  

  <script>
      export default {
          name: "FooterView",
      }
  </script>
  
  <style scoped>
  .container{
    margin-top: 20px;
  }

  .page-icon{
		margin:20px 0 0 0;      /*设置距离顶部20像素*/
		font-size:0;            /*修复行内元素之间空隙间隔*/
		text-align:center;      /*设置内容居中显示*/
	} 
	
	.page-icon a,.page-disabled,.page-next{
		border:1px solid #ccc;
		border-radius:3px;
		padding:4px 10px 5px;
		font-size:14PX;         /*修复行内元素之间空隙间隔*/
		margin-right:10px;
	}
	
	.page-icon a{
		text-decoration:none;
		color:black;
	}
	
	.page-current{
		color:#005aa0;
		padding:4px 12px 5px;
		font-size:14px;     /*修复行内元素之间空隙间隔*/
	}
	
	.page-disabled{
		color:#ccc;
	}
	
	.page-next i,.page-disabled i{
		cursor:pointer;      
		display:inline-block;    
		width:5px;
		height:9px;
        background-color: #ccc;
	}
	.page-disabled i{
		background-position:-80px -608px;
		background-color: white;
	}
 
	.page-next i{
		background-position:-62px -608px;
        background-color: white;
	}

  </style>