registerForm.addEventListener('submit',function (e) {
     let message="";
     let email=document.getElementById('email');
     let name=document.getElementById('name');
     let password=document.getElementById('password');
     let text=document.getElementById('text');


    if (!(email.value.length>0 && email.value.length!=null)){
         message+="Email is empty\n";
         e.preventDefault();
     }
    if (!(name.value.length>0 && name.value.length!=null)){
        message+="Name is empty\n";
        e.preventDefault();
    }
    if (!(password.value.length>=8 && email.value.length!=null)){
        message+="Password is empty or less than 8 characters\n";
        e.preventDefault();
    }
    text.textContent=message;
},false);