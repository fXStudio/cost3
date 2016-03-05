/**
 * 超时统计
 * @Author FXStudio.com.cn(AjaxFan)
 * @Date 2014-09-26
 */
$(document).ready(function(){
	//兼容火狐、IE8  
    var showMask = function(){
        $("#mask").css("height", $(document).height());  
        $("#mask").css("width", $(document).width());
        $("#mask").show();  
    }();
    
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
				$('#' + key).addClass("nonzero");
			}
			calcByDay();
     	  	$("#mask").hide(); 
		}
	});

	/**
	 * 统计每一天的加班总时间
	 */
	var calcByDay = function(){
		$("td[id^='@']").each(function(){
			// 加班时间总长度
			var total = 0;
			$("td[id$='_"+ $(this).attr("abbr") +"']").each(function(){
				total = eval(total + '+' + $(this).html());
			});
			// 设置加班的总时间长度
			$(this).html(total);
		});
	}
});