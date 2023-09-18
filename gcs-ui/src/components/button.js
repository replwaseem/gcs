import styled from 'styled-components';

export const Button = styled.button`
  color: orange;
  background-color: blue;
  font-size: 1em;
  margin: 2px;
  padding: 0.25em 1em;
  border: 2px solid darkred;
  border-radius: 3px;
  :hover {
    background-color: skyblue;
    color: white;
  }
  background-image: url("./img/editor-1s-47px.gif");
`;

export default  Button;