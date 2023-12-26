import React, { useState, useEffect } from "react";
import LoginServiceCustomer from "../Service/LoginServiceCustomer";

export default function Test1() {
  const [data, setData] = useState({});

  useEffect(() => {
    LoginServiceCustomer.getUser()
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  });

  return (
    <div>
      <h1>{data.dateOfBirth}</h1>
    </div>
  );
}
