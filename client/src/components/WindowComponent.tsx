import './WindowComponent.css';
import { Button, styled, TextField } from '@mui/material';
import { useState } from 'react';
import axios from 'axios';
import moment from 'moment';

type WindowConfig = {
    name: string;
    endpoint: string;
}

const CustomTextField = styled(TextField)({
    '& label': {
        color: '#bbb',
    },
    '& label.Mui-focused': {
        color: '#fff',
    },
    '& .MuiInput-underline:after': {
        borderBottomColor: '#fff',
    },
    '& .MuiOutlinedInput-root': {
        '& fieldset': {
            borderColor: '#bbb',
        },
        '&:hover fieldset': {
            borderColor: '#fff',
        },
        '&.Mui-focused fieldset': {
            borderColor: '#fff',
        },
    },
});


export function WindowComponent({ config }: { config: WindowConfig }) {
    const { name: windowName, endpoint } = config;
    const [output, setOutput] = useState('');
    const [clientIds, setClientIds] = useState(['1']);
    const handleWindowClick = async () => {
        const timestamp = moment().format('YYYY-MM-DD h:mm:ss');
        try {
            const clientId = clientIds.shift() as string;
            clientIds.push(clientId);
            const url = `${endpoint}?clientId=${clientId}`;
            const { data } = await axios.get(url);
            setOutput(`${output}${timestamp} - ${data}\n`);
        } catch (e: any) {
            setOutput(`${output}${timestamp} - ${e.message}\n`);
        }
    }


    return (
        <div className="window-container">
            <Button variant="contained" style={{ fontSize: '30px', marginBottom: '20px' }} onClick={handleWindowClick}>
                {windowName}
            </Button>
            <CustomTextField
                label="Client IDs"
                variant="outlined"
                placeholder="1,2,3..."
                defaultValue={clientIds.join('')}
                onChange={(e) => {
                    setClientIds(e.target.value.split(','));
                }}
                sx={{ input: { color: '#fff' } }} />
            <Button variant="contained" style={{ fontSize: '20px', marginTop: '20px' }} onClick={() => setOutput('')}>
                Clear Output
            </Button>
            <p className="window-output">{output}</p>
        </div>
    );
}