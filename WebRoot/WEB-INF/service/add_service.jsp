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
            //保存成功的提示信息
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

            //自动查询账务账号
            function searchAccount() {
            	//重置数据
            	$("#account_id").val("");
            	$("#account_login_name").val("");
            	
            	//取得身份证号
                var a=$("#idcard_no").val();
                
                $.post(
                	"searchAccount.do",
                	{"a":a},
                	function(data){
                		if(data.success){
                			$("#validate_msg_short").text(data.msg1);
                			$("#validate_msg_long").text(data.msg2);
                			$("#account_login_name").val(data.account_login_name);
                			$("#account_id").val(data.account_id);
                		}else{
                			$("#validate_msg_short").text(data.msg1);
                			$("#validate_msg_long").text(data.msg2);
                		}
                	}
                );
            }
            
            //检查输入参数,不合条件的return false，使表单无法提交
            function check(){
            	//检查2次输入密码是否相同
            	var pwd1=$("#pwd1").val();
            	var pwd2=$("#pwd2").val();
            	//密码不能为空
        		if(pwd1  == ""){
        			$("#pwd_msg").text("密码不能为空").addClass("error_msg");
        			return false;
        		}
        		//校验两次输入密码是否一致
        		if(!(pwd1 == pwd2)){
        			$("#pwd_msg").text("两次输入密码不一致").addClass("error_msg");
        			return false;
        		}
        		//校验两次输入密码是否一致
        		if(pwd1 == pwd2){
        			$("#pwd_msg").text("两次输入密码一致").removeClass("error_msg");
        		}
        		
        		//其余各项参数检查。。。待完成。。。
        		
        		
        		return true;
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
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <form action="addService.do" method="post" onsubmit="return check()" class="main_form">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text"s class="width180" id="idcard_no" />
                    <input type="button" value="查询账务账号" class="btn_search_large" onclick="searchAccount();" />
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="validate_msg_short">请输入身份证号码。</div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                    <input type="text" id="account_login_name" onkeyup="searchAccounts(this);" readonly="readonly" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="validate_msg_long"></div>
                </div>
                
                <input type="hidden" id="account_id" name="account_id"  />
                
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                    <select name="cost_id" >
                    	<c:forEach items="${costs }"  var="c">
                    		<option value="${c.cost_id }">${c.name }</option>
                    	</c:forEach>
                    </select>                        
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input name="unix_host" type="text" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">15 长度，符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                    <input name="os_username" type="text"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input name="login_passwd" type="password" id="pwd1" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="pwd_msg">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password" id="pwd2"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long" >两次密码必须相同</div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save"  />
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
