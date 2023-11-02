//listen for authentication changes
auth.onAuthStateChanged(user =>{
    if(user){
        console.log('User logged in: ', user)
        checklogin(user)
    }
    else{
        console.log('User logged out')
        checklogin()
    }
})


signup
const signupForm = document.querySelector('#signupForm')
signupForm.addEventListener('submit',(e)=>{
    e.preventDefault();
    //get user info
    const email=signupForm['signupEmail'].value;
    const password=signupForm['signupPassword'].value;
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
const loginForm = document.querySelector("#signinForm")
loginForm.addEventListener("submit",(e)=>{
    e.preventDefault(); 
    const email=loginForm['signinEmail'].value;
    const password=loginForm['signinPassword'].value;

    firebase.auth().signInWithEmailAndPassword(email,password).then(cred=>{
        console.log(cred.user);
        document.getElementById('Login').click();
        loginForm.reset();
    })
})


const loggedOutLinks = document.querySelectorAll(".logged-out")
const loggedInLinks = document.querySelectorAll(".logged-in")
const checklogin = (user)=>{
    if (user){
        loggedInLinks.forEach(item=>item.style.display="block")
        loggedOutLinks.forEach(item=>item.style.display="none")
    }else{
        loggedInLinks.forEach(item=>item.style.display="none")
        loggedOutLinks.forEach(item=>item.style.display="block");
    }
}
