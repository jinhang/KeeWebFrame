function moduleSearchInit(){
	var $mainBlock = $('#searchForm>.dhxlist_obj_dhx_skyblue>.dhxlist_base>.block_item_label_left');
	
//	$("#searchForm").bind("mouseout",function(e){
//		if(e.pageX<$(this).position().left||e.pageX>$(this).position().left+$(this).width()
//				||e.pageY<$(this).position().top)
//		{
//			if($mainBlock.first().nextAll().css('display') == 'block'){
//				$('#toggleFormBtn').click();
//			}
//		}
//	});
	
	$(".search").bind("click",function(e){
		if($mainBlock.first().nextAll().css('display') == 'block'){
			$('#toggleFormBtn').click();
		}
		return true;
	});
	if($mainBlock && $mainBlock.length < 2){
		$('#toggleFormBtn').css('display','none');
	}
	else {
		$mainBlock.first().nextAll().css('display','none');
	}
	$mainBlock.first().nextAll().css('display','none');
	$('#toggleFormBtn').addClass('toggleFormBg').children('span').empty();
	$('#toggleFormBtn').click(function(e){
		if($mainBlock.first().nextAll().css('display') == 'none'){
			$mainBlock.first().nextAll().css('display','block');
			$('#searchForm').addClass('searchFormExpand');
			$(this).removeClass('toggleFormButt').addClass('toggleonFormButt');
			$('#tabSearch').show();
			$('#searchForm').mouseleave(function(){a = 1;});
			$("#searchForm").mouseover(function(){a = 0;});
		}else{
			$mainBlock.first().nextAll().css('display','none');
			$('#searchForm').removeClass('searchFormExpand');
			$(this).removeClass('toggleonFormButt').addClass('toggleFormButt');
			$('#tabSearch').hide();
		}
	});
	a = 0;
	
	$('body').click(function () {
		if (a) {
			$mainBlock.first().nextAll().css('display','none');
			$('#searchForm').removeClass('searchFormExpand');
			$('#toggleFormBtn').removeClass('toggleonFormButt').addClass('toggleFormButt');
			$('#tabSearch').hide();
		}
	});
	
	try{
		parent.$('body').click(function () {
			if (a) {
				$mainBlock.first().nextAll().css('display','none');
				$('#searchForm').removeClass('searchFormExpand');
				$('#toggleFormBtn').removeClass('toggleonFormButt').addClass('toggleFormButt');
				$('#tabSearch').hide();
			}
		});
	}catch(e){
		parent.parent.$('body').click(function () {
			if (a) {
				$mainBlock.first().nextAll().css('display','none');
				$('#searchForm').removeClass('searchFormExpand');
				$('#toggleFormBtn').removeClass('toggleonFormButt').addClass('toggleFormButt');
				$('#tabSearch').hide();
			}
		});
	}
	
	var $twinInput = $('#searchForm .dhxlist_base .dhxlist_base .dhxlist_block');	
	$twinInput.children('div:first').addClass('clearfix');
	$twinInput.find('input.dhxlist_txt_textarea').addClass('twinInput');
	$twinInput.find('.dhxlist_txt_label').eq(1).addClass('middleLabel');
	
	var $blockCell = $('#searchForm>.dhxlist_obj_dhx_skyblue>.dhxlist_base>.block_item_label_left>.dhxlist_block>div');
	$blockCell.each(function(index) {
		$(this).children().each(function(i){
			$(this).addClass('blockCell'+(i+1));
		})
	});
	
}

 