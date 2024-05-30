<%@page import="entity.Cart"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的购物车 </title>
  </head>
  <script type="text/javascript">
  			function checkAll(obj){
  				var b=obj.checked;
				//选中
 				var list=document.getElementsByClassName("cb");
  				if(b){
  					//alert(list.length);
  					for(var i=0;i<list.length;i++){
  						//每一个class属性为cb的多选按钮
  						list[i].checked=true;
  					}
  				}else{
  					for(var i=0;i<list.length;i++){
  						//每一个class属性为cb的多选按钮
  						list[i].checked=false;
  					}
  				}
  				
  			}
  			
  			function unChecked(obj){
  				var b=obj.checked;
  				console.log(!b);
  				if(!b){
  					//取消勾选
  					document.getElementById("cbox").checked=false;
  				}else{
  					//当前按钮选中
  					var flag=true;
  					var list=document.getElementsByClassName("cb");
  					for(var i=0;i<list.length;i++){
  						if(!list[i].checked){
  							flag=false;
  						}
  					}
  					if(flag){
  						document.getElementById("cbox").checked=true;
  					}
  				}
  				
  			}
  			
  			function changeNum(id){
  				var inp=document.getElementById("num"+id);
  				var value=inp.value;
  				//考虑校验
  				var reg=/^[0-9]+$/;
  				var b=reg.test(value);
  				if(!b||value<=0){
  					alert("请输入正确的数字");
  					return;
  				}
  				//alert(value);
  				location="<%=request.getContextPath() %>/modifyNum.cart?num="+value+"&id="+id;
  				
  			}
  			
  			function cc(){
  				var list=document.getElementsByClassName("cb");
  				if(list==null||list.length==0){
  					alert("没有任何商品删除个锤子！");
  					return;
  				}
  				var aaa="";
  				var flag=false;
  				for(var i=0;i<list.length;i++){
  					if(list[i].checked){
  						aaa+=list[i].value+",";
  						flag=true;
  					}
  				}
  				if(!flag){
  					alert("请勾选正确的商品进行删除！");
  					return;
  				}
  				//alert(str); xx,xx,xx,
  				location="<%=request.getContextPath()%>/clearCheck.cart?value="+aaa;
  			}
  </script>
  <body>
  		<%
  		List<Cart> carts=(List<Cart>)request.getAttribute("carts");
  		%>
    	<h1>我的购物车</h1>
    	<hr>
    	<table border="1">
    		<tr>
    			<th><input id="cbox" type="checkbox" onclick="checkAll(this);">全选</th>
    			<th>序号</th>
    			<th>商品</th>
    			<th>数量</th>
    			<th>价格</th>
    			<th>操作</th>
    		</tr>
    		<%
    		double sum=0;
    		if(carts.size()!=0){
    			int n=1;
    			for(Cart cart:carts){
    				sum=sum+cart.getPrice();
    		%>
    		<tr>
    			<td><input value="<%=cart.getId()%>" onclick="unChecked(this);" class="cb" type="checkbox"></td>
    			<td><%=n++ %></td>
    			<td><%=cart.getProduct().getName() %></td>
    			<td>
					<input type="text" size="1px" id="num<%=cart.getId()%>" value="<%=cart.getNum()%>">
				</td>
    			<td><%=cart.getPrice() %></td>
    			<td>
    				<a onclick="return confirm('是否删除当前商品？');" href="<%=request.getContextPath() %>/removeById.cart?id=<%=cart.getId()%>">删除</a>
    				<a href="javascript:;" onclick="changeNum(<%=cart.getId()%>);">修改</a>
    			</td>
    		</tr>
    		<%
    			}
    		}else{
    		%>
    		<tr>
    			<td style="text-align: center;" colspan="6">没有购买任何商品</td>
    		</tr>
    		<%
    			}
    		%>
    		
    	</table>
    	<hr>
    	总计：<%=sum %>元<br>
    	<a href="javascript:;" onclick="cc();">删除选中项</a>
    	<a href="<%=request.getContextPath() %>/clear.cart">清空购物车</a>
    	<a href="<%=request.getContextPath() %>/list.product">继续购物</a>
    	<a href="order.html">结算</a>
  </body>
</html>
