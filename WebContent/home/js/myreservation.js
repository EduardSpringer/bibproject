document.addEventListener("DOMContentLoaded",init);

function init(){
	var deletelink = document.getElementsByClassName("deletelink");
	
	var i;
    for (i = 0; i < deletelink.length; i++) {
    	deletelink[i].addEventListener("click", confirmDeleteLink);
    }
	
}

function confirmDeleteLink(evt){
	var deletebox = confirm("Wollen Sie den Termin wirklich löschen?");
	
	if(!deletebox){
		evt.preventDefault();
	}
}