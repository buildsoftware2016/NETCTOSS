<%@page pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script language="javascript" type="text/javascript" src="../js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
            
            function set_msg(){
            	$("#name_msg").text("不能为空，且为20长度的字母、数字和汉字的组合").removeClass("error_msg");
            }
            
            function check_param(){
            	//检查角色名
            	//获取角色名
            	var name=$("#name").val();
            	//正则表达式
            	var reg=/^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/;
            	
            	if(!reg.test(name)){
            		$("#name_msg").text("不符合格式要求").addClass("error_msg");
            		return false;
            	}
            	//$("#name_msg").text("格式正确").removeClass("error_msg");
            	
            	//角色名格式正确后，再确定是否重复
            	var role_id=$("#role_id").val();
            	$.post(
            		"checkRoleName2.do",
            		{"name":name,"role_id":role_id},
            		function(data){
            			if(!data){
            				$("#name_msg").text("角色名重复").addClass("error_msg");
            				return false;
            			}
            			$("#name_msg").text("可以使用").removeClass("error_msg");
            			nameFlag=true;
            		}           		
            	);
            	
            	
            	//获取所有选中的checkbox的value值
            	var checkObjs=$(":checkbox[name='moduleIds']:checked");       
            	if(checkObjs.length==0){
            		//一个都没选中
            		$("#module_msg").addClass("error_msg");
            		return false;
            	}
            	$("#module_msg").removeClass("error_msg");
            	
            	//页面脚本的执行与异步请求是并行的，
            	//当发起异步请求时，脚本不会等待，而是
            	//直接向下执行
            	var timer=setInterval(function(){
            		if(nameFlag){
            			//停止循环
            			clearInterval(timer);
            			document.forms[0].submit();
            		}
            	},100);
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <jsp:include page="/WEB-INF/main/menu.jsp" /> 
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="modiRole.do" method="post" class="main_form">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input name="name" id="name" type="text" class="width200" value="${role.name }"  onfocus="set_msg();" />
                    <span class="required">*</span>
                    <div class="validate_msg_medium error_msg"  id="name_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <input name="role_id" id="role_id" value="${role.role_id }" type="hidden"/>
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                            <c:forEach var="m" items="${modules }">
                            	<li><input name="moduleIds" type="checkbox" value="${m.module_id }" 
                            		<c:forEach var="rm" items="${r_modules }">
                            			<c:if test="${m.module_id==rm.module_id }">checked</c:if>
                            		</c:forEach>
                            	/>${m.name }</li>	
                            </c:forEach>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny" id="module_msg">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="check_param();" />
                    <input type="button" value="取消" class="btn_save" onclick="history.back()" />
                </div>
            </form> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
