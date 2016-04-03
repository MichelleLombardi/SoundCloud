$(document).ready(function () {
    var modal1 = $("#myModal1"); // modal sign in
    var loading = $("#loading"); // modal loading
    var signin = $("#signin");// button que abre modal1
    var x = $("#x");// button x que cierra modal1
    var x1 = $("#x1");// button x que cierra modal2
    var signinContB = $("#signinContB");// button que abre modalloading y registra
    var signinEmail = $("#signinEmail");// input  email del modal1
    var signinPass = $("#signinPass");// input password del modal1
    var anuncio = $("#anuncio");//div anuncion error signin
    var cancel = $("#cancel");// button que cancela y cierra el modal1
    var cancel1 = $("#cancel1");// button que cancela y cierra el modal2
    var wrong = $("#wrong");
    var wrongemail = $("#wrongemail");
    var wrong1 = $("#wrong1");
    var check = $("#check");
    var wrongpass = $("#wrongpass");
    var modal2 = $("#myModal2"); // modal sign in
    var createaccount = $("#createaccount");//botton que abre modal2

    console.log(signin)

    //click en el boton signin
    signin.click(function() {
        check.css({display: "none"});
        signinEmail.css({border:"1px solid #DBE1EB"});
        wrong.css({display:"none"});
        wrongemail.css({display:"none"});
        signinPass.css({border:"1px solid #DBE1EB"});
        wrong1.css({display:"none"});
        wrongpass.css({display:"none"});
        signinEmail.val( "" );
        signinPass.val( "" );
        modal1.css({display: "block"});
        setInterval(function(){
            if(signinPass.val().length==20){
                check.css({display:"block"});
            }else{
                check.css({display:"none"});
            }
        },500);

    });

    //click en el boton x del modal 1
    x.click(function() {
        modal1.css({display: "none"});
    });
    //click en el boton x del modal 2
    x1.click(function() {
        modal2.css({display: "none"});
    });
    //click en el boton canceldel modal1
    cancel.click(function() {
        modal1.css({display: "none"});
    });
    //click en el boton cancel del modal2
    cancel1.click(function() {
        modal2.css({display: "none"});
    });

    //click en el boton sign in del modal1
    signinContB.click(function() {
        checkSignin();
        if(aux1==false){
            if(aux2==false){
                signinEmail.css({border:"1px solid #DBE1EB"});
                wrong.css({display:"none"});
                wrongemail.css({display:"none"});
                signinPass.css({border:"1px solid #DBE1EB"});
                wrong1.css({display:"none"});
                wrongpass.css({display:"none"});
                loading.css({display: "block"});

                var i=0;
                if(i==1){
                    setTimeout(function(){
                        loading.css({display:"none"});
                        modal1.css({display:"none"});
                    }, 5000);
                }else{
                    setTimeout(function(){
                        loading.css({display:"none"});
                        signinEmail.val( "" );
                        signinPass.val( "" );
                        anuncio.css({display:"block"});
                        setTimeout(function(){
                            anuncio.css({display:"none"});
                        },3000);
                    }, 5000);
                }
            }else{
                aux2=false;
                signinEmail.css({border:"1px solid #DBE1EB"});
                wrong.css({display:"none"});
                wrongemail.css({display:"none"});
            }
        }else{
            if(aux2==false){
                aux1=false;
                signinPass.css({border:"1px solid #DBE1EB"});
                wrong1.css({display:"none"});
                wrongpass.css({display:"none"});
            }else{
                aux1=false;
                aux2=false;
            }
        }
    }); //fin oclick signinContB

    //funcion que corrabora que se hayan incluido todos los datos
    var aux1=false;
    var aux2=false;
    function checkSignin(){
        var email= signinEmail.val();
        var password= signinPass.val();
        if(email.trim()==""){
            signinEmail.css({border:"1px solid red"});
            wrong.css({display:"block"});
            wrongemail.css({display:"block"});
            aux1=true;
        }
        if(password.trim()==""){
            signinPass.css({border:"1px solid red"});
            wrong1.css({display:"block"});
            wrongpass.css({display:"block"});
            aux2=true;
        }
    }

    //click en la parte transparente del div para el signin: salida
    $(window).click(function(event) {
        if (event.target == modal1) {
            modal1.css({display: "none"});
        }
        if (event.target == modal2) {
            modal2.css({display: "none"});
        }
    });

    //click en el boton create account
    createaccount.click(function() {
        modal2.css({display: "block"});
    });

});