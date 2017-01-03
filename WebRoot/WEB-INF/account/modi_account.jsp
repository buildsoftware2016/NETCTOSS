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
        <script language="javascript" type="text/javascript">
        	//页面加载完后，自动执行此函数，生成生日
        	window.onload=function(){
        		//重置生日
            	$("#birthdate").val("");
            	//取得身份证号
            	var idcard_no = $("#idcard_no1").val();
            	//从身份证中截取出生日
            	//123456 19910203 1234 --> 1991-02-03
            	var year = idcard_no.substring(6,10);
            	var month = idcard_no.substring(10,12);
            	var day = idcard_no.substring(12,14);
            	var birth = year + "-" + month + "-" + day;
            	$("#birthdate").val(birth);
        	}
        	
        	//校验推荐人身份证号
        	function check_idcardno() {
            	//取得身份证号
            	var idcard_no = $("#idcard_no2").val();
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

            //显示修改密码的信息项
            function showPwd(chkObj) {
                if (chkObj.checked)
                    document.getElementById("divPwds").style.display = "block";
                else
                    document.getElementById("divPwds").style.display = "none";
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
            <div id="save_result_info" class="save_fail">保存失败，旧密码错误！</div>
            <form action="modiAccount.do" method="post" class="main_form">
                    <!--必填项-->
                    <div class="text_info clearfix"><span>账务账号ID：</span></div>
                    <div class="input_info">
                        <input name="account_id" type="text" value="${account.account_id }" readonly class="readonly" />
                        <div class="validate_msg_long error_msgs">不能变更</div>
                    </div>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input name="real_name" type="text" value="${account.real_name }" />
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg">20长度以内的汉字、字母和数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>身份证：</span></div>
                    <div class="input_info">
                        <input type="text" value="${account.idcard_no}" id="idcard_no1" readonly class="readonly" />
                        <div class="validate_msg_long error_msgs">不能变更</div>
                    </div>
                    <div class="text_info clearfix"><span>登录账号：</span></div>
                    <div class="input_info">
                        <input type="text" value="${account.login_name}" readonly class="readonly"  />
                        <div class="validate_msg_long error_msgs">不能变更</div>        
                        <!--                
                        <div class="change_pwd">
                            <input id="chkModiPwd" type="checkbox" onclick="showPwd(this);" />
                            <label for="chkModiPwd">修改密码</label>
                        </div>
                        -->
                    </div>
                    <!--修改密码部分-->
                    <!--
                    <div id="divPwds">
                        <div class="text_info clearfix"><span>旧密码：</span></div>
                        <div class="input_info">
                            <input type="password"  />
                            <span class="required">*</span>
                            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>新密码：</span></div>
                        <div class="input_info">
                            <input type="password"  />
                            <span class="required">*</span>
                            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>重复新密码：</span></div>
                        <div class="input_info">
                            <input type="password"  />
                            <span class="required">*</span>
                            <div class="validate_msg_long">两次密码必须相同</div>
                        </div>  
                    </div>      
                    -->             
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input name="telephone" value="${account.telephone}" type="text" class="width200"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                        <input name="recommender_idcard_no" value="${recommender_idcard_no }" id="idcard_no2" type="text" onblur="check_idcardno();"/>
                        <div class="validate_msg_long error_msgs" id="idcard_no_msg">正确的身份证号码格式</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                        <input name="birthdate" type="text" value="${account.birthdate}" readonly class="readonly" id="birthdate" />
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input name="email" value="${account.email}" type="text" class="width200"/>
                        <div class="validate_msg_medium">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                        <select name="occupation" >
                            <option value="0" <c:if test="${account.occupation==0}">selected</c:if> >干部</option>
                            <option value="1" <c:if test="${account.occupation==1}">selected</c:if> >学生</option>
                            <option value="2" <c:if test="${account.occupation==2}">selected</c:if> >技术人员</option>
                            <option value="3" <c:if test="${account.occupation==3}">selected</c:if> >其他</option>
                        </select>                        
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <input type="radio" name="gender" value="0" <c:if test="${account.gender==0}">checked</c:if> id="female" onclick="feeTypeChange(1);" />
                        <label for="female">女</label>
                        <input type="radio" name="gender" value="1" <c:if test="${account.gender==1}">checked</c:if> id="male" onclick="feeTypeChange(2);" />
                        <label for="male">男</label>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <input name="mailaddress" value="${account.mailaddress}" type="text" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <input name="zipcode" value="${account.zipcode}" type="text"/>
                        <div class="validate_msg_long">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <input name="qq" value="${account.qq}" type="text"/>
                        <div class="validate_msg_long">5到13位数字</div>
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