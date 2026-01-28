import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    let [l,setL]=useState({
      email:"test@gmail.com",
      password:"12345"
    });
    let api="http://40.0.20.123:4040/login"
    let navigate=useNavigate();
  async function submitHandler(){
    console.log(l);
    let res=await fetch(api,{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(l),
    })

    let data=await res.text();
    console.log(" api response : "+data);
    if(data=="Login successful"){
        alert(" Login sucess ..✅");
        navigate("/admin");
        

    }

  }
  return (
    <>
      <div>
        <h3>Login</h3>
        <input
          type="text"
          placeholder="email"
         onChange={(e)=>{setL({...l,email:e.target.value})}}
        />
        <br />
        <input
          type="text"
          placeholder="password"
               onChange={(e)=>{setL({...l,password:e.target.value})}}
        />
        <br />
        <br />
        <button onClick={submitHandler}>submit</button>
        <br />
      </div>
    </>
  );
}

export default Login;
