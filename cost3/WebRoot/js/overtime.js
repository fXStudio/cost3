$(document).ready(function(){
	// 隐藏编辑器
	$('#editor').css("visibility", "hidden");
	
	//兼容火狐、IE8  
    var showMask = function(){
        $("#mask").css("height", $(document).height());  
        $("#mask").css("width", $(document).width());
        $("#mask").show();  
    }();
	
	/**
	 * 编辑区以外的元素
	 */
	$('#editor').blur(function(){
		$(this).appendTo($('body'));
		// 隐藏编辑器
		$(this).css("visibility", "hidden");
		
		// 处理之前被选中的文本
		handlePreObj();
	});

	/**
	 * 文本编辑框对象
	 */
	$('td.CEditor').each(function(){
		// 文本编辑框被选中
		$(this).click(function(){
			// 如果两次选中的是同一个组件，则不做任何操作
			if($(this).attr('class').indexOf('selected') > 0){
				return;
			}
			
			/***************** 新选中的组件处理方式 ***************/
			// 设置当前组件为选中状态
			$(this).addClass('selected');
			// 释放隐藏组件
			$('#editor').css("visibility", "visible");
			// 将当前组件的内容写入到编辑框中
			$('#editor').val((function(v){
				if(v.match(/^[0-9]+$/g)){
					return v;
				}
				return '';
			})($.trim($(this).html())));
			// 保存修改前的信息
			$(this).data('preval', $('#editor').val());
			// 清空当前组件
			$(this).empty();
			// 把编辑框添加到指定的列对象中
			$(this).append($('#editor'));
			// 选中全部
			$('#editor').select();
			/***************** 新选中的组件处理方式 ***************/
		});
	});
	
	/**
	 * 控制组件的录入
	 */
	$('#editor').keydown(function(event){
		// 键盘案件的ASCII码值
	    var code = parseInt(event.keyCode);
	    
	    // 设置可接受的输入内容格式
	    return (code >= 96 && code <= 104 || 
	        code >= 48 && code <= 56 || 
	        code >= 37 && code <= 40 ||
	        code === 8 || code === 9);
	});
	
	// 保存修改项
	$('#submit').click(function(){
		var param = '';// 参数
		
		// 遍历所有的已修改数据
		$('.modified').each(function(){
			if(param.length > 1){
				param += '&';
			}
			param += $(this).attr('id');
			param += '=';
			param += $.trim($(this).html());
		});
		// 把修改的数据复制到隐藏域中
		$(':hidden[name="overtime.clientparam"]').val(param);
		// 提交变更表单到服务器
		$('form[name=form1]').submit();
	});
	
	// 重置键设置
	$('#reset').click(function(){ window.location.reload(); });
	
	/**
	 * 拉取最新的数据
	 */
	$.ajax({
	   	type: "POST",
	   	url: "spi/listMonthOvers.action",
	   	data: (function(){
	   		var itemid = $(':hidden[name="overtime.itemid"]').val();
	   		var date = $(':hidden[name="overtime.date"]').val();
	   		
	   		return "overtime.itemid=" + itemid + "&overtime.date=" + date;
	   	})(),
	   	success: function(msg){
			var jsonobj = eval("(" + msg + ")");
			for(var key in jsonobj){
				$('#' + key).html(jsonobj[key]);
				$('#' + key).css("color", "red");
				$('#' + key).addClass('nonzero');
     	  		$('#' + key).attr('abbr', jsonobj[key]);
			}
     	  	$("#mask").hide(); 
		}
	});
	
	/**
	 * 处理之前被选中的文本对象
	 */
	var handlePreObj = function(){
		// 获得刚刚失去焦点的组件对象
		var preobj = $('.selected');
	
		// 判断这个组件是否存在
		if(preobj){
			// 去除这个组件的选中标志
			preobj.removeClass('selected');
			// 设置这个组件的当前值
			preobj.append((function(v){
				if(v && v.length > 0){
					return v;
				}
				return preobj.data('preval');
			})($('#editor').val()));
			// 保存数据修改
			saveUpdate(preobj);
		}
	};
	
	/**
	 * 记录数据的修改
	 */
	var saveUpdate = function(obj){
		// 初始化的数据
		var old = obj.attr('abbr');
		// 当前的数据
		var cur = $.trim(obj.html());
		
		// 为修改的数据打上标识
		if(old != cur){
			obj.addClass('modified'); return;
		}
		// 取消已还原数据的修改标识
		obj.removeClass('modified');
	}
});