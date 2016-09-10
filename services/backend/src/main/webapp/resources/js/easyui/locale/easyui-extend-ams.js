 $.extend($.fn.validatebox.defaults.rules, {
           
            treeUrl: {// 验证资源树地址
            	validator: function (value) {
//            		不全是下划线,字母,斜杠(?![A-Za-z]+$)
            		return /^(?![_]+$)(?![\/]+$)([a-z]*[A-Z]*[0-9]*[\/]*[_]*[.]*[-]*)+$/i.test(value);
            	},
            	message: '不合法;不能有空格和特殊字符'
            },
            resName: {// 验证资源或者资源树名称
            	validator: function (value) {
            		return /^([\u4E00-\uFA29]*[a-z]*[A-Z]*[0-9]*[_]*[.]*[-]*[\/]*)+$/i.test(value);
            	},
            	message: '不合法,不能有空格和特殊字符!'
            },
            funCode: {// 验证资源代码
            	validator: function (value) {
            		return /^(?![_]+$)([a-z]*[A-Z]*[0-9]*[_]*[\/]*[.]*[-]*)+$/i.test(value);
            	},
            	message: '不合法,不能有空格和特殊字符!'
            },
            reqSecretKey: {//企业密钥
            	validator: function (value) {
            		return /^[A-Za-z0-9]+$/i.test(value);
            	},
            	message: '不合法,只能是数字或者字母,不能有空格!'
            },
            proDomain: {//企业URL
            	validator: function (value) {
            		
            		var strRegex = '^((https|http|ftp|rtsp|mms)?://)' 
            			+ '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@ 
            			+ '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184 
            			+ '|' // 允许IP和DOMAIN（域名） 
            			+ '([0-9a-z_!~*\'()-]+.)*' // 域名- www. 
            			+ '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名 
            			+ '[a-z]{2,6})' // first level domain- .com or .museum 
            			+ '(:[0-9]{1,4})?' // 端口- :80 
            			+ '((/?)|' // a slash isn't required if there is no file name 
            			+ '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'; 
            			var re=new RegExp(strRegex); 
            			
            			if (re.test(value)) { 
            			return (true); 
            			} else { 
            			return (false); 
            			} 
            	},
            	message: '不合法,请输入正常的url地址,如:http://www.xingmima.com!'
            },
            newPassword :{//密码,+号可以隔绝空格
            	validator: function (value) {
            		return /^(?![_]+$)[a-zA-Z0-9_]+$/i.test(value);
            	},
            	message: '密码不合法（允许5-18字符,允许字母数字下划线,不允许空格）!'
            },
            equalPass : { 
            	validator: function (value, param) { 
            		return $(param[0]).val() == value; 
            	},
            	 message: '密码和确认密码不匹配'
            },
            admName :{//用户名,+号可以隔绝空格
            	validator: function (value) {
            		return /^(?![_]+$)[a-zA-Z0-9_]+$/i.test(value);
            	},
            	message: '用户名不合法（允许5-18字符,允许字母数字下划线,不允许空格）!'
            }
        });