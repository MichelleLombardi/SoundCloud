/* global localStorage */
/* global $ */
$(document).ready(function () {
    var modal1 = $("#myModal1"); // modal sign in
    var loading = $("#loading"); // modal loading
    var signin = $("#signin");// button que abre modal1
    var x = $("#x");// button x que cierra modal1
    var signinContB = $("#signinContB");// button que abre modalloading y logea
    var signinEmail = $("#signinEmail");// input  email del modal1
    var signinPass = $("#signinPass");// input password del modal1
    var anuncio = $("#anuncio");//div anuncion error signin
    var cancel = $("#cancel");// button que cancela y cierra el modal1
    var wrong = $("#wrong"); //imagen wrong signin email
    var wrongemail = $("#wrongemail"); //parrafo de wrong signin email
    var wrong1 = $("#wrong1"); //imagen wrong signin password
    var check = $("#check"); //imagen check signin password
    var wrongpass = $("#wrongpass"); //parrafo de wrong signin password

    var modal2 = $("#myModal2"); // modal create account
    var createaccount = $("#createaccount");//botton que abre modal2
    var x1 = $("#x1");// button x que cierra modal2
    var cancel1 = $("#cancel1");// button que cancela y cierra el modal2
    var caBotton = $("#caBotton"); //botton que abre modalloading y registra
    var wrongfni = $("#wrong2"); //imagen wrong createaccount fisrt name
    var wrongfn = $("#wrongfn"); //parrafo de wrong createaccount fisrt name
    var wronglni = $("#wrong3"); //imagen wrong createaccount last name
    var wrongln = $("#wrongln"); //parrafo de wrong createaccount last name
    var wrongbi = $("#wrong4"); //imagen wrong createaccount birthday
    var wrongb = $("#wrongb"); //parrafo de wrong createaccount birthday
    var wrongei = $("#wrong5"); //imagen wrong createaccount email
    var wronge = $("#wronge"); //parrafo de wrong createaccount email
    var wrongpi = $("#wrong6"); //imagen wrong createaccount password
    var wrongp = $("#wrongpw"); //parrafo de wrong createaccount password
    var caFN = $("#createaccountFN");// input  firt name del modal2
    var caLN = $("#createaccountLN");// input last name del modal2
    var caB = $("#createaccountB");// input  birthday del modal2
    var caE = $("#createaccountE");// input email del modal2
    var caP = $("#createaccountP");// input password del modal2
    var check2 = $("#check1"); //imagen check create account password
    var anuncio2 = $("#anuncio2");//div anuncion error signin
    
    
    var logoutdiv = $("#logoutdiv");
    var opclo = $("#opclo");
    var logout = $("#logout");//boton para cerrar la sesion
    
    var uluyo =$("#uploadyourown"); // abre el modal3
    var modal3 = $("#myModal3"); // modal upload 
    var x2 = $("#x2");// button x que cierra modal3
    var cancel2 = $("#cancel2");// button que cancela y cierra el modal3
    var uBotton = $("#uBotton"); //botton que abre modalloading y sube la cancion
    var song = $("#song"); //input para cargar la musica
    var wrongsi = $("#wrong7"); //imagen wrong uluyo song
    var wrongs = $("#wrongtrack"); //parrafo de wrong uluyo song
    var namesong = $("#namesong"); //input para nombre cancion
    var wrongnsi = $("#wrong8"); //imagen wrong uluyo name song
    var wrongns = $("#wrongns"); //parrafo de wrong uluyo name song
    var descpsong = $("#descpsong"); //input para descripcion de la musica
    var wrongdsi = $("#wrong9"); //imagen wrong uluyo description
    var wrongds = $("#wrongds"); //parrafo de wrong uluyo description
    var tagsong = $("#tagsong"); //input para tag de la musica
    var wrongdti = $("#wrong10"); //imagen wrong uluyo tag
    var wrongt = $("#wrongt"); //parrafo de wrong uluyo tag
    

    //click en el boton signin
    signin.click(function () {
        check.css({display: "none"});
        signinEmail.css({border: "1px solid #DBE1EB"});
        wrong.css({display: "none"});
        wrongemail.css({display: "none"});
        signinPass.css({border: "1px solid #DBE1EB"});
        wrong1.css({display: "none"});
        wrongpass.css({display: "none"});
        var vacio = "";
        signinEmail.val(vacio);
        signinPass.val(vacio);
        modal1.css({display: "block"});
        setInterval(function () {
            if (signinPass.val().length == 20) {
                check.css({display: "block"});
            } else {
                check.css({display: "none"});
            }
        }, 500);
    });
    
    //click en el boton uploadyourown
    var auxuluyo=0;
    uluyo.click(function(){
    	if(auxuluyo==1){
    	    var vacio = "";
    	    song.val(vacio);
            wrongsi.css({display: "none"});
            wrongs.css({display: "none"});
            namesong.val(vacio);
            wrongnsi.css({display: "none"});
            wrongns.css({display: "none"});
            descpsong.val(vacio);
            wrongdsi.css({display: "none"});
            wrongds.css({display: "none"}); 
            tagsong.val(vacio);
            wrongdti.css({display: "none"});
            wrongt.css({display: "none"});
    		modal3.css({display: "block"});
    	}else{
    		check.css({display: "none"});
            signinEmail.css({border: "1px solid #DBE1EB"});
            wrong.css({display: "none"});
            wrongemail.css({display: "none"});
            signinPass.css({border: "1px solid #DBE1EB"});
            wrong1.css({display: "none"});
            wrongpass.css({display: "none"});
            var vacio = "";
            signinEmail.val(vacio);
            signinPass.val(vacio);
            modal1.css({display: "block"});
            setInterval(function () {
                if (signinPass.val().length == 20) {
                    check.css({display: "block"});
                } else {
                    check.css({display: "none"});
                }
            }, 500);
    	}
    });

    //click en el boton create account
    createaccount.click(function () {
        caFN.css({border: "1px solid #DBE1EB"});
        wrongfni.css({display: "none"});
        wrongfn.css({display: "none"});
        caLN.css({border: "1px solid #DBE1EB"});
        wronglni.css({display: "none"});
        wrongln.css({display: "none"});
        caB.css({border: "1px solid #DBE1EB"});
        wrongbi.css({display: "none"});
        wrongb.css({display: "none"});
        caE.css({border: "1px solid #DBE1EB"});
        wrongei.css({display: "none"});
        wronge.css({display: "none"});
        caP.css({border: "1px solid #DBE1EB"});
        wrongpi.css({display: "none"});
        wrongp.css({display: "none"});
        caFN.val(vacio);
        caLN.val(vacio);
        caB.val(vacio);
        caE.val(vacio);
        caP.val(vacio);
        modal2.css({display: "block"});
        setInterval(function () {
            if (caP.val().length == 20) {
                check2.css({display: "block"});
            } else {
                check2.css({display: "none"});
            }
        }, 500);
    });

    //click en el boton x del modal 1
    x.click(function () { modal1.css({display: "none"}); });

    //click en el boton x del modal 2
    x1.click(function () { modal2.css({display: "none"}); });
    
    //click en el boton x del modal 3
    x2.click(function () { modal3.css({display: "none"}); });

    //click en el boton canceldel modal1
    cancel.click(function () { modal1.css({display: "none"}); });

    //click en el boton cancel del modal2
    cancel1.click(function () { modal2.css({display: "none"}); });
    
    //click en el boton cancel del modal3
    cancel2.click(function () { modal3.css({display: "none"}); });

    //click en el boton sign in del modal1
    var vacio = "";
    signinContB.click(function () {
        checkSignin();
        if (aux1 == false) {
            if (aux2 == false) {
                signinEmail.css({border: "1px solid #DBE1EB"});
                wrong.css({display: "none"});
                wrongemail.css({display: "none"});
                signinPass.css({border: "1px solid #DBE1EB"});
                wrong1.css({display: "none"});
                wrongpass.css({display: "none"});
                loading.css({display: "block"});

                console.log("Tratamos de hacer login: ");
                $.ajax({
                    url: "./login",
                    type: "POST",
                    data: {
                        email: signinEmail.val(),
                        pass: signinPass.val()
                    },
                    success: function (data) {
                        if( !data.error ) {
                            setTimeout(function(){ 
	                        	loading.css({display: "none"});
	                            modal1.css({display: "none"});

	                            localStorage.user = JSON.stringify(data);
	                            console.log(data);
	
	                            signin.css({"display":"none"});
	                            createaccount.css({"display":"none"});
	                            logoutdiv.css({"display":"inline"});
	                            logoutdiv.text(data.nombre+" "+data.apellido);
	                            opclo.css({"display":"block"});
	                            auxuluyo=1;
                            }, 5000);
                        }
                        else {
                            var error = data.error;
                            signinEmail.val(vacio);
                            signinPass.val(vacio);
                           
                            setTimeout(function(){ 
	                            loading.css({display: "none"});
	                            anuncio.css({display: "block"});
	                            auxuluyo=0;
	
	                            setTimeout(function () {
	                                anuncio.css({display: "none"});
	                            }, 3000);
                            }, 5000);

                            console.log(error);
                        }
                    },
                    error: function (err) {
                        signinEmail.val(vacio);
                        signinPass.val(vacio);
                        
                        setTimeout(function(){ 
	                        loading.css({display: "none"});
	                        anuncio.css({display: "block"});
	                        auxuluyo=0;
	
	                        setTimeout(function () {
	                            anuncio.css({display: "none"});
	                        }, 3000);
                        }, 5000);
                        console.log(err);
                    }
                });
            } else {
                aux2 = false;
                signinEmail.css({border: "1px solid #DBE1EB"});
                wrong.css({display: "none"});
                wrongemail.css({display: "none"});
            }
        } else {
            if (aux2 == false) {
                aux1 = false;
                signinPass.css({border: "1px solid #DBE1EB"});
                wrong1.css({display: "none"});
                wrongpass.css({display: "none"});
            } else {
                aux1 = false;
                aux2 = false;
            }
        }
    }); //fin oclick signinContB

    //funcion que corrobora que se hayan incluido todos los datos del login
    var aux1 = false;
    var aux2 = false;
    function checkSignin() {
        var email = signinEmail.val();
        var password = signinPass.val();
        if (email.trim() == vacio) {
            signinEmail.css({border: "1px solid red"});
            wrong.css({display: "block"});
            wrongemail.css({display: "block"});
            aux1 = true;
        }
        if (password.trim() == vacio) {
            signinPass.css({border: "1px solid red"});
            wrong1.css({display: "block"});
            wrongpass.css({display: "block"});
            aux2 = true;
        }
    }
 
    //click en el boton create account del modal2
    caBotton.click(function () {
        checkCreateAccount();
        if (aux3 == false) {
            if (aux4 == false) {
                if (aux5 == false) {
                    if (aux6 == false) {
                        if (aux7 == false) {
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                            loading.css({display: "block"});

                            console.log("Tratamos de crear una cuenta: ");
                            //corroborar email
                            $.ajax({
                                url: "./signup",
                                type: "POST",
                                data: {
                                    firstName: caFN.val(),  // input  firt name del modal2
                                    lastName: caLN.val(),   // input last name del modal2
                                    birthday: caB.val(),    // input  birthday del modal2
                                    email: caE.val(),       // input email del modal2
                                    pass: caP.val()         // input password del modal2
                                },
                                success: function (data) {
                                    // Si no hay error
                                    if( !data.error ) {  // si el email no existe
                                        setTimeout(function(){
                                            loading.css({display: "none"});
                                            modal2.css({display: "none"});
                                            
                                            localStorage.user = JSON.stringify(data);
                                            console.log(data);
                                            
                                            signin.css({"display":"none"});
            	                            createaccount.css({"display":"none"});
            	                            logoutdiv.css({"display":"inline"});
            	                            logoutdiv.text(data.nombre+" "+data.apellido);
            	                            opclo.css({"display":"block"});
            	                            auxuluyo=1;
                                            
                                        }, 5000);
                                    }
                                    else {
                                        var error = data.error;
                                        caFN.val(vacio);
                                        caLN.val(vacio);
                                        caB.val(vacio);
                                        caE.val(vacio);
                                        caP.val(vacio);
                                        
                                        setTimeout(function(){
                                            loading.css({display: "none"});
            	                            anuncio2.css({display: "block"});
            	                             auxuluyo=0;
    
                                            setTimeout(function () {
                                                anuncio2.css({display: "none"});
                                            }, 3000);
                                        }, 5000);
                                        console.log(error);
                                    }
                                },
                                error: function (err) {
                                    
                                        caFN.val(vacio);
                                        caLN.val(vacio);
                                        caB.val(vacio);
                                        caE.val(vacio);
                                        caP.val(vacio);
                                        
                                        setTimeout(function(){
                                            loading.css({display: "none"});
            	                            anuncio2.css({display: "block"});
            	                             auxuluyo=0;
    
                                            setTimeout(function () {
                                                anuncio2.css({display: "none"});
                                            }, 3000);
                                        }, 5000);
                                        console.log(err);
                                }
                            });
                        }else {
                            aux7 = false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else {
                        if (aux7 == false) {
                            aux6 = false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        } else {
                            aux7 = false;
                            aux6 = false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                        }
                    }
                }else {
                    if (aux6 == false) {
                        if(aux7==false){
                            aux5=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }else{
                            aux5=false;
                            aux7=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux5=false;
                            aux6=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        }else{
                            aux5=false;
                            aux6=false;
                            aux7=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                        }
                    }
                }
            }else{
                if(aux5==false){
                    if(aux6==false){
                        if(aux7==false){
                            aux4=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }else{
                            aux4=false;
                            aux7=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux4=false;
                            aux6=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        }else{
                            aux4=false;
                            aux6=false;
                            aux7=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                        }
                    }
                }else{
                    if(aux6==false){
                        if(7==false){
                            aux4=false;
                            aux5=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }else{
                            aux4=false;
                            aux5=false;
                            aux7=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux4=false;
                            aux5=false;
                            aux6=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        }else{
                            aux4=false;
                            aux5=false;
                            aux6=false;
                            aux7=false;
                            caFN.css({border: "1px solid #DBE1EB"});
                            wrongfni.css({display: "none"});
                            wrongfn.css({display: "none"});
                        }
                    }
                }
            }
        }else{
            if(aux4==false){
                if(aux5==false){
                    if(aux6==false){
                        if(aux7==false){
                            aux3=false;
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }else{
                            aux3=false;
                            aux7=false;
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux3=false;
                            aux6=false;
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        }else{
                            aux3=false;
                            aux6=false;
                            aux7=false;
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                        }
                    }
                }else{
                    if(aux6==false){
                        if(aux7==false){
                            aux3=false;
                            aux5=false;
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }else{
                            aux3=false;
                            aux5=false;
                            aux7=false;
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux3=false;
                            aux5=false;
                            aux6=false;
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        }else{
                            aux3=false;
                            aux5=false;
                            aux6=false;
                            aux7=false;
                            caLN.css({border: "1px solid #DBE1EB"});
                            wronglni.css({display: "none"});
                            wrongln.css({display: "none"});
                        }
                    }
                }
            }else{
                if(aux5==false){
                    if(aux6==false){
                        if(aux7==false){
                            aux3=false;
                            aux4=false;
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                        }else{
                            aux3=false;
                            aux4=false;
                            aux7=false;
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux3=false;
                            aux4=false;
                            aux6=false;
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                        }else{
                            aux3=false;
                            aux4=false;
                            aux7=false;
                            aux6=false;
                            caB.css({border: "1px solid #DBE1EB"});
                            wrongbi.css({display: "none"});
                            wrongb.css({display: "none"});
                        }
                        
                    }
                }else{
                    if(aux6==false){
                        if(aux7==false){
                            aux3=false;
                            aux4=false;
                            aux5=false;
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }else{
                            aux3=false;
                            aux4=false;
                            aux5=false;
                            aux7=false;
                            caP.css({border: "1px solid #DBE1EB"});
                            wrongpi.css({display: "none"});
                            wrongp.css({display: "none"});
                        }
                    }else{
                        if(aux7==false){
                            aux3=false;
                            aux4=false;
                            aux5=false;
                            aux6=false;
                            caE.css({border: "1px solid #DBE1EB"});
                            wrongei.css({display: "none"});
                            wronge.css({display: "none"});
                        }else{
                            aux3=false;
                            aux4=false;
                            aux5=false;
                            aux6=false;
                            aux7=false;
                        }
                    }
                }
            }
        }
    }); //fin onclick caBotton

    //funcion que corrabora que se hayan incluido todos los datos del registro
    var aux3 = false;
    var aux4 = false;
    var aux5 = false;
    var aux6 = false;
    var aux7 = false;
    function checkCreateAccount() {
        var firstname = caFN.val();
        var lastname = caLN.val();
        var birthday = caB.val();
        var caemail = caE.val();
        var capassword = caP.val();

        if (firstname.trim() == vacio) {
            caFN.css({border: "1px solid red"});
            wrongfni.css({display: "block"});
            wrongfn.css({display: "block"});
            aux3 = true;
        }

        if (lastname.trim() == vacio) {
            caLN.css({border: "1px solid red"});
            wronglni.css({display: "block"});
            wrongln.css({display: "block"});
            aux4 = true;
        }

        if (birthday.trim() == vacio) {
            caB.css({border: "1px solid red"});
            wrongbi.css({display: "block"});
            wrongb.css({display: "block"});
            aux5 = true;
        }

        if (capassword.trim() == vacio) {
            caP.css({border: "1px solid red"});
            wrongpi.css({display: "block"});
            wrongp.css({display: "block"});
            aux6 = true;
        }

        if (caemail.trim() == vacio) {
            caE.css({border: "1px solid red"});
            wronge.css({display: "block"});
            wrongei.css({display: "block"});
            aux7 = true;
        }
    }
    
    
    //click en el boton upload del modal3
    var vacio = "";
    uBotton.click(function () {
        checkUluyo();
        if (aux11 == false) {
            if (aux9 == false) {
                if(aux10==false){
                    if(aux8==false){
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                        loading.css({display: "block"});
                        
                        console.log("Tratamos de subir una cancion: ");
                        //aqui van las opciones success y error
                    }else{
                        aux8=false;
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }
                }else{
                    if(aux8==false){
                        aux10=false;
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }else{
                        aux8=false;
                        aux10=false;
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }
                }
            }else{
                if(aux10==false){
                    if(aux8==false){
                        aux9=false;
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }else{
                        aux9=false;
                        aux8=false;
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }
                }else{
                    if(aux8==false){
                        aux9=false;
                        aux10=false;
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }else{
                        aux9=false;
                        aux8=false;
                        aux10=false;
                        tagsong.css({border: "1px solid #DBE1EB"});
                        wrongdti.css({display: "none"});
                        wrongt.css({display: "none"});
                    }
                }
            }//fin aux9
        }else{
            if(aux9==false){
                if(aux10==false){
                    if(aux8==false){
                        aux11=false;
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                    }else{
                        aux11=false;
                        aux8=false;
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                    }//8
                }else{
                    if(aux8==false){
                        aux11=false;
                        aux10=false;
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        
                    }else{
                        aux11=false;
                        aux10=false;
                        aux8=false;
                        namesong.css({border: "1px solid #DBE1EB"});
                        wrongnsi.css({display: "none"});
                        wrongns.css({display: "none"});
                    }
                }//10
            }else{
               if(aux10==false){
                    if(aux8==false){
                        aux11=false;
                        aux9=false;
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                    }else{
                        aux11=false;
                        aux8=false;
                        aux9=false;
                        descpsong.css({border: "1px solid #DBE1EB"});
                        wrongdsi.css({display: "none"});
                        wrongds.css({display: "none"}); 
                    }//8
                }else{
                    if(aux8==false){
                        aux11=false;
                        aux10=false;
                        aux9=false;
                        wrongsi.css({display: "none"});
                        wrongs.css({display: "none"});
                        
                    }else{
                        aux11=false;
                        aux10=false;
                        aux8=false;
                        aux9=false;
                    }
                } 
            }//9
        }//fin aux11
    }); //fin oclick uBotton

    //funcion que corrabora que se hayan incluido todos los datos del uluyo
    var aux8 = false;
    var aux9 = false;
    var aux10 = false;
    var aux11 = false;
    function checkUluyo() {
        var songfile = song.val();
        var ns = namesong.val();
        var descs = descpsong.val();
        var tags = tagsong.val();
        
        if (songfile.trim() == vacio) {
            wrongsi.css({display: "block"});
            wrongs.css({display: "block"});
            aux8 = true;
        }

        if (ns.trim() == vacio) {
            namesong.css({border: "1px solid red"});
            wrongnsi.css({display: "block"});
            wrongns.css({display: "block"});
            aux9 = true;
        }

        if (descs.trim() == vacio) {
            descpsong.css({border: "1px solid red"});
            wrongdsi.css({display: "block"});
            wrongds.css({display: "block"}); 
            aux10 = true;
        }

        if (tags.trim() == vacio) {
            tagsong.css({border: "1px solid red"});
            wrongdti.css({display: "block"});
            wrongt.css({display: "block"});
            aux11 = true;
        }
    }

    //click en la parte transparente del div para el signin: salida
    $(window).click(function (event) {
        if (event.target == modal1[0]) {
            modal1.css({display: "none"});
        }
        if (event.target == modal2[0]) {
            modal2.css({display: "none"});
        }
        if (event.target == modal3[0]) {
            modal3.css({display: "none"});
        }
    });
    
    // Esto verifica si hay una sesion guardada
    // y en tal caso la inicia si existe
    var user = JSON.parse(localStorage.user ? localStorage.user : "{}");
    console.log(user);
    console.log("Hay una sesion activa?:");
    if( user ) {
        $.ajax({
            url: "./login",
            method: "GET",
            headers: {
                Authorization: user.token
            },
            success: function (data) {
                // Si no hay error
                if( !data.error ) {
                    localStorage.user = JSON.stringify(data);
                    console.log(data);
                    console.log("Hay una sesion activa?: si");
                    signin.css({"display":"none"});
                    createaccount.css({"display":"none"});
                    logoutdiv.css({"display":"inline"});
                    logoutdiv.text(data.nombre+" "+data.apellido);
                    opclo.css({"display":"block"});
                    auxuluyo=1;
                }
                else {
                    var error = data.error;
                    localStorage.clear();
                    console.log(error);
                    console.log("Hay una sesion activa?: no");
                    logoutdiv.css({"display":"none"});
                    opclo.css({"display":"none"});
                    auxuluyo=0;
                }
            },
            error: function (err) {
                localStorage.clear();
                console.log(err);
                logoutdiv.css({"display":"none"});
                opclo.css({"display":"none"});
                auxuluyo=0;
            }
        })
    }

    // Cuando se hace click en logout
    opclo.click(function() {
        console.log("Cerraremos la sesion:");
        $.ajax({
            url: "./login",
            method: "DELETE",
            success: function (data) {
                // Borramos el localstorage
                if( !data.error ) {
                    localStorage.user = JSON.stringify(data);
                    console.log(data);
                    signin.css({"display":"block"});
                    createaccount.css({"display":"block"});
                    logoutdiv.css({"display":"none"});
                    opclo.css({"display":"none"});
                    auxuluyo=0;
                }
                else {
                	var error = data.error;
                    localStorage.clear();
                    console.log(error);
                    console.log("Hay una sesion activa?: no");
                    signin.css({"display":"block"});
                    createaccount.css({"display":"block"});
                    logoutdiv.css({"display":"none"});
                    opclo.css({"display":"none"});
                    auxuluyo=0;
                }
            },
            error: function (err) {
            	var error = err;
                localStorage.clear();
                console.log(error);
                console.log("Hay una sesion activa?: no");
                signin.css({"display":"block"});
                createaccount.css({"display":"block"});
                logoutdiv.css({"display":"none"});
                opclo.css({"display":"none"});
                auxuluyo=0;
            }
        })
    });
    
});