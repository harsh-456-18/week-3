let signupObject={
    name:"test",
    email:"b16@gmail.com",
    password:"456456"
}

console.log("sign up data: "+JSON.stringify(signupObject))
console.log("\n\t JSON OBJECT \n")
console.log("sign up data: "+JSON.stringify(signupObject,null,2))
let api="http://40.0.20.123:4040/api/signup"

async function signup(){
    let n=document.getElementById("n").value;
    let e=document.getElementById("e").value;
    let p=document.getElementById("p").value;
    let obj={
        name:n,
        email:e,
        password:p,
}
    console.log(" data: "+JSON.stringify(obj,null,2));
    let res=await fetch(api,{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(obj),
    })
    let data=await res.text();
    console.log(" api response : "+data);

    if(data=="signup success"){
        alert("signup success..✅")
    }
}