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
        	//校验身份证号
        	function check_idcardno() {
        		//重置生日
            	$("#birthdate").val("");
            	
            	//取得身份证号
            	var idcard_no = $("#idcard_no").val();
            	//校验身份证号是否为空
            	if(idcard_no == "") {
            		$("#idcard_no_msg").text("身份证号不能为空.").addClass("error_msg");
            		return;
            	}
            	//校验身份证格式
            	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
            	if(!reg.test(idcard_no)) {
            		$("#idcard_no_msg").text("身份证号格式不对.").addClass("error_msg");
            		return;
            	}
            	//身份证验证通过，给予正确提示，并移除错误样式
            	$("#idcard_no_msg").text("身份证号有效.").removeClass("error_msg");
            	
            	//从身份证中截取出生日
            	//123456 19910203 1234 --> 1991-02-03
            	var year = idcard_no.substring(6,10);
            	var month = idcard_no.substring(10,12);
            	var day = idcard_no.substring(12,14);
            	var birth = year + "-" + month + "-" + day;
            	$("#birthdate").val(birth);
        	}
        	
        	//校验密码设置
        	function check_pwd(){
        		
        		var pwd1=$("#pwd1").val();
        		var pwd2=$("#pwd2").val();
        		//密码不能为空
        		if(pwd1  == ""){
        			$("#pwd_msg").text("密码不能为空").addClass("error_msg");
        			return;
        		}
        		//校验两次输入密码是否一致
        		if(!(pwd1 == pwd2)){
        			$("#pwd_msg").text("两次输入密码不一致").addClass("error_msg");
        			return;
        		}
        		//校验两次输入密码是否一致
        		if(pwd1 == pwd2){
        			$("#pwd_msg").text("两次输入密码一致").removeClass("error_msg");
        		}
        		
        	}
        	
        	
        	
        
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

            //显示选填的信息项
            function showOptionalInfo(imgObj) {
                var div = document.getElementById("optionalInfo");
                if (div.className == "hide") {
                    div.className = "show";
                    imgObj.src = "../images/hide.png";
                }
                else {
                    div.className = "hide";
                    imgObj.src = "../images/show.png";
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
            <!--保存成功或者失败的提示消息-->     
            <div id="save_result_info" class="save_fail">保存失败，该身份证已经开通过账务账号！</div>
            <form action="addAccount.do" method="post" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input type="text" name="real_name" value="" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" name="idcard_no" id="idcard_no" onblur="check_idcardno();" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="idcard_no_msg">正确的身份证号码格式</div>
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input type="text" name="login_name"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password" name="login_passwd" id="pwd1" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="pwd_msg">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password"  id="pwd2" onblur="check_pwd();" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="pwd_msg">两次密码必须相同</div>
                </div>     
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" name="telephone" class="width200"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
                </div>                
                <!--可选项-->
                <div class="text_info clearfix"><span>可选项：</span></div>
                <div class="input_info">
                    <img src="../images/show.png" alt="展开" onclick="showOptionalInfo(this);" />
                </div>
                <div id="optionalInfo" class="hide">
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                        <input name="recommender_idcard_no" type="text"/>
                        <div class="validate_msg_long">正确的身份证号码格式</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                        <input name="birthdate" type="text"  readonly class="readonly" id="birthdate" />
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input name="email" type="text" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                        <select name="occupation" >
                            <option value="0">干部</option>
                            <option value="1">学生</option>
                            <option value="2">技术人员</option>
                            <option value="3">其他</option>
                        </select>                        
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <input type="radio" name="gender" value="0" checked="checked" id="female" />
                        <label for="female">女</label>
                        <input type="radio" name="gender" value="1" id="male" />
                        <label for="male">男</label>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <input name="mailaddress" type="text" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <input name="zipcode" type="text"/>
                        <div class="validate_msg_long">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <input name="qq" type="text"/>
                        <div class="validate_msg_long">5到13位数字</div>
                    </div>                
                </div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save"  />
                    <input type="button" value="取消" class="btn_save" onclick="javascript:history.back();" />
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