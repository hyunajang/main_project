	$(document).ready(function(){
		$('#image_preview').hide();
	});

	function loadFile(event){
		var reader = new FileReader();
		reader.onload = function(event) {
			$('#image_preview').attr('src', event.target.result);
		};
		reader.readAsDataURL(event.target.files[0]);
		$('#image_preview').show();
		$('#contents').css('height','970px');
	}
	