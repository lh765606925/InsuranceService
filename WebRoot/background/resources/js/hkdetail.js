$(function () {
    jQuery('#agree').validationEngine("attach", {
                        promptPosition : "topLeft",
                        maxErrorsPerField : true,
                        showOneMessage : true
                    });
     
	var product = $('#proInfo').val(),
	    proInfo = serializeMetaInfo(product);
	  if ( !isEmptyObj(proInfo) ){
	    $('#goinsure').click(function(){
	    	 if($('#agree').validationEngine('validate')){
		        location.href = '../insure.html' + '#' + proInfo;
		    }
		
	    });
	  }

	$('#seedetail').click(function () {
				var pId = $("#productId").val();
        location.href = pId+'_detail.html';
	});




});