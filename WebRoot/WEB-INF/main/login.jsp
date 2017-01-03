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
        	function check_param(){
        		//验证账号密码
        		var reg=/^\w{3,30}$/;
        		var code=$("#code").val();
        		var pwd=$("#pwd").val();
        		if(!reg.test(code)){
        			$("#msg").text("账号格式不对").addClass("required");
        			return;
        		}
        		$.post(
        			"checkCodePwd.do",
        			{"code":code,"pwd":pwd},
        			function(data){
        				if(!data.success){
        					$("#msg").text(data.msg).addClass("required");
        					return;
        				}
        				if(data.success){
        					location.href="index.do";
        				}
        			} 
        		);
        	}
        </script>
    </head>
    <body class="index">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="admin_code" id="code" type="text" class="width150" /></td>
                    <td class="login_error_info"><span  id="code_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="password" id="pwd" type="password" class="width150" /></td>
                    <td><span class="required"  id="pwd_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="" type="text" class="width70" /></td>
                    <td><img src="images/valicode.jpg" alt="验证码" title="点击更换" /></td>  
                    <td><span class="required" id="valicode_msg"></span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:check_param();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required" id="msg"></span></td>                
                </tr>
            </table>
        </div>
    </body>
</html>
