//使用ajax加载数据字典，生成select
//参数：数据字典类型（dict_type_code）,下拉选框标签的id,生成下拉选项时，select标签的name属性值，需要回显时，选中哪个option
function loadSelect(typecode,positionId,selectname,selectedId){
	//创建select对象，将name属性指定
	var $select = $("<select name="+selectname+"></select>");
	//添加提示选项
	$select.append($("<option value=''>---请选择---</option>"));
	//使用jquery的ajax方法，访问后台Action
		
	$.post("${pageContext.request.contextPath}/BaseDictAction", {dict_type_code : typecode},
		function(data) {
		//返回json的数组对象，对其遍历
			 $.each(data, function(i, json) {
			 //每次遍历一个action对象
			 	var $option = $("<option value='"+json['dict_id']+"'>"+json["dict_item_name"]+"</option>");
			 	//判断是否需要回显，如果需要使其被选中
			 	if(json['dict_id']==selectedId){
			 		$option.attr("selected","selected");
			 	}
			 	//并添加到select对象
			 	$select.append($option);
			});
		}, "json");
		//将组装好的select对象放入页面指定位置
		$("#"+positionId).append($select);

	}