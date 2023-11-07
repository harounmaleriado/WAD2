//listen for authentication changes
auth.onAuthStateChanged(user =>{
    if(user){
        console.log('User logged in: ', user)
    }
    else{
        console.log('User logged out')
    }
})


//signup
const signupForm = document.querySelector('#signup-form')
signupForm.addEventListener('submit',(e)=>{
    e.preventDefault();
    //get user info
    const email=signupForm['signup-email'].value;
    const password=signupForm['signup-password'].value;
    //sign user up
    firebase.auth().createUserWithEmailAndPassword(email,password).then(cred=>{
        console.log(cred);
        document.getElementById('Register').click();
        signupForm.reset();
    })
})

//signout
const signout = document.querySelector('#signout');
signout.addEventListener("click",(e)=>{
    e.preventDefault();
    firebase.auth().signOut().then(()=>
    console.log("User signed out"))
})

//login
const loginForm = document.querySelector("#login-form")
loginForm.addEventListener("submit",(e)=>{
    e.preventDefault();
    const email=loginForm['login-email'].value;
    const password=loginForm['login-password'].value;

    firebase.auth().signInWithEmailAndPassword(email,password).then(cred=>{
        console.log(cred.user);
        document.getElementById('Login').click();
        loginForm.reset();
    })
})