import React from 'react';
import axios from 'axios';

const Chat = () => {

    //text in box
    const [message,setMessage] = React.useState('');

    //list of messages
    const [messageList, setMessageList] = React.useState([]);

    const handleMessageUpdate =(e) => {
        setMessage(e.target.value);
    };

    const handleSubmit =() => {
        console.log(message);
        const body = {
            message : message,
        };
        axios.post('/submit-message', body) //trigger this endpoint and send this body data
        .then(fetchMessages);
        setMessage('');
    }

    const fetchMessages =() => {
        axios.get('/get-messages')  //asyc
        .then((res) => {
            //res is what the spark server sent back
            console.log(res.data);
            setMessageList(res.data); //save for using on the page
        });
    };


    React.useEffect(() => {
            //trigger  this only one time
            fetchMessages();
    }, []);


    return(

        <div>
            <h1>Chat</h1>
            <input value={message} onChange={handleMessageUpdate}/>
            <button onClick={handleSubmit}>Submit </button>

            <div>
                {messageList.map(object => <div>{object.message}</div>)}
            </div>

        </div>

    );
};

export default Chat;