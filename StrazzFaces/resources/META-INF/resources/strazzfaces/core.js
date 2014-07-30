(function(window) {
    
    if(window.StrazzFaces) {
        PrimeFaces.debug("StrazzFaces already loaded, ignoring duplicate execution.");
        return;
    }
    
    var StrazzFaces = {
    	changeTheme: function(newValue){
    		$(document).trigger("StrazzFaces.themeChanged",newValue);
    	}
    }
    
    window.StrazzFaces = StrazzFaces;

    var originalChangeTheme = PrimeFaces.changeTheme;
    
	PrimeFaces.changeTheme = function(newValue){
		originalChangeTheme(newValue);
		StrazzFaces.changeTheme(newValue);
	}
    
})(window);