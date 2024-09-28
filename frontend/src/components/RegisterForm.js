import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import './RegisterForm.css'; // 별도의 CSS 파일로 스타일링

const RegisterForm = () => {
    const searchParams = new URLSearchParams(useLocation().search);
    const kakaoId = searchParams.get('kakaoId');  // 카카오 ID만 받아옴

    const [name, setName] = useState('');  // 이름은 빈 문자열로 초기화
    const [phoneNumber, setPhoneNumber] = useState('');
    const [department, setDepartment] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log("Form Data:", { name, phoneNumber, department, kakaoId });
        try {
            const response = await axios.post('/login/register', {
                kakaoId,
                name,
                phoneNumber,
                department
            });
            alert('회원가입 성공!');
        } catch (error) {
            const errorMessage = error.response ? JSON.stringify(error.response.data) : error.message;
            console.error("회원가입 실패:", errorMessage);
            alert(`회원가입에 실패했습니다. 에러: ${errorMessage}`);
        }
    };

    return (
        <div className="register-container">
            <h1 className="register-title">FEFE</h1>
            <form onSubmit={handleSubmit} className="register-form">
                <div className="form-group">
                    <label>이름</label>
                    <input
                        type="text"
                        placeholder="김가천"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>핸드폰 번호</label>
                    <input
                        type="tel"
                        placeholder="XXX XXXX XXXX"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>학과</label>
                    <select
                        value={department}
                        onChange={(e) => setDepartment(e.target.value)}
                        required
                    >
                        <option value="">학과를 선택해주세요</option>
                        <option value="경영학과">경영학과</option>
                        <option value="컴퓨터공학과">컴퓨터공학과</option>
                        <option value="인공지능학과">인공지능학과</option>
                        <option value="전기공학과">전기공학과</option>
                        <option value="영문학과">영문학과</option>
                        <option value="간호학과">간호학과</option>
                        <option value="화학공학과">화학공학과</option>
                        <option value="건축학과">건축학과</option>
                        <option value="생명과학과">생명과학과</option>
                        <option value="심리학과">심리학과</option>
                        <option value="물리학과">물리학과</option>
                        <option value="기계공학과">기계공학과</option>
                        <option value="전자공학과">전자공학과</option>
                        <option value="수학과">수학과</option>
                        <option value="경제학과">경제학과</option>
                    </select>
                </div>
                <button type="submit" className="register-button">회원가입</button>
            </form>
        </div>
    );
};

export default RegisterForm;