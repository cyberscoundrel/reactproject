import React from 'react';
import axios from 'axios';

const Home = () => {
//change over time
const [toggle,setToggle] = React.useState(false); //initial value is false
const [text, setText] = React.useState(' ');

  const a = 'hello world'

  //handler function
  //call this function when user clicks the button
  const myHandler = () => {
    console.log('Handler is clicked');
    setToggle(!toggle);

  };

  const handleText = (e) => {
    const inputText = e.target.value;
    setText(inputText);

  }

  const handleSubmit =() => {
    console.log(text);
    axios.get('/test-endpoint?name=' + text);

  };


  return (
    <div style={{background : toggle ? 'blue' : 'green'}}>
      <h1>Home</h1>
      <p>Here is some test</p>
      <div>{a}</div>
      <button onClick={myHandler}>Click me!</button>
      <input value={text} onChange={handleText} />
      <button onClick={handleSubmit}>Submit text</button>
    </div>
  );
};

export default Home;
