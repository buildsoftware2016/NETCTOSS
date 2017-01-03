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
            
            //检查姓名
            function check_name(){
            	var name=$("#name").val();
            	var reg=/^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/;
            	if(reg.test(name)){
            		$("#name_msg").text("格式正确").removeClass("error_msg");
            		//flag=true;
            		return true;
            	}else{
            		$("#name_msg").text("20长度以内的汉字、字母、数字的组合").addClass("error_msg");
            		return false;
            	}
            }
            
            //检查电话
            function check_telephone(){
            	var telephone=$("#telephone").val();
            	var reg1=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;//手机
            	var reg2=/\d{3}-\d{8}|\d{4}-\d{7}/; //固话
            	//检查格式
            	if(reg1.test(telephone) || reg2.test(telephone)){
            		$("#telephone_msg").text("格式正确").removeClass("error_msg");
            		//flag=true;
            		return true;
            	}else{
            		$("#telephone_msg").text("正确的电话号码格式：手机或固话").addClass("error_msg");
            		return false;
            	}           	
            }
            
            //检查Email
            function check_email(){
            	var email=$("#email").val();
            	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            	//检查格式
            	if(reg.test(email)){
            		$("#email_msg").text("格式正确").removeClass("error_msg");
            		//flag=true;
            		return true;
            	}else{
            		$("#email_msg").text("正确的 email 格式").addClass("error_msg");
            		return false;
            	}
            }
            
            //至少选择一个角色
            function check_role(){
            	var checkObjs=$(":checkbox[name=roleIds]:checked");
            	if(checkObjs.length==0){
            		$("#role_msg").text("至少选择一个").addClass("error_msg");
            		return false;
            	}
            	if(checkObjs.length>0){
            		$("#role_msg").text("选中").removeClass("error_msg");
            		//flag=true;
            		return true;
            	}
            }
            
            function check_param(){            
            	if(check_name() && check_telephone() && check_email() && check_role() ){
            		document.forms[0].submit();
            		}        	           	
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
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="modiAdmin.do" method="post" class="main_form">
            <input name="admin_id" type="hidden" value="${admin.admin_id }" />
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input name="name" id="name" type="text" value="${admin.name }" onblur="check_name()" />
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="name_msg">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${admin.admin_code}"  /></div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input name="telephone" id="telephone" type="text" value="${admin.telephone }" onblur="check_telephone()" />
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="telephone_msg">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input name="email" id="email" type="text" class="width200" value="${admin.email }" onblur="check_email()"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium" id="email_msg">50长度以内，正确的 email 格式</div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                            <ul>
                               	<c:forEach var="r" items="${roles }">
                            		<li><input name="roleIds" type="checkbox" value="${r.role_id }" onclick="check_role()" 
                            			<c:forEach var="ri" items="${roleIds }">
                            				<c:choose>
                            					<c:when test="${r.role_id==ri.ROLE_ID }">checked</c:when>
                            					<c:otherwise></c:otherwise>
                            				</c:choose>
                            			</c:forEach>
                            		/>${r.name }</li>
                            	</c:forEach>
                            </ul>
                        </div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny" id="role_msg">至少选择一个</div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" onclick="check_param()" />
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
