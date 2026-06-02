import React, { useState } from "react";
import EvaluateBanknote from "./components/EvaluateBanknote";
import GradeGoals from "./components/GradeGoals";
import { InputFeatures } from "./constants/enums";
import { banknoteApi } from "./services/api";

function App() {
    const initialFormState = Object.keys(InputFeatures).reduce(
        (acc, key) => {
            acc[key] = InputFeatures[key][0];
            return acc;
        },
        { id: "AA123456789" }
    );

    const [banknoteState, setBanknoteState] = useState(initialFormState);
    const [evaluationResult, setEvaluationResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleEvaluate = async () => {
        setLoading(true);
        try {
            const data = await banknoteApi.evaluateBanknote(banknoteState);
            setEvaluationResult(data);
        } catch (err) {
            alert("Error during evaluation.");
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen bg-gray-100 py-10 px-4 sm:px-6 lg:px-8">
            <div className="max-w-4xl mx-auto">
                <header className="text-center mb-10">
                    <h1 className="text-4xl font-extrabold text-gray-900 tracking-tight sm:text-5xl">
                        Paper Banknote Grading System
                    </h1>
                </header>

                <main className="space-y-8">
                    <EvaluateBanknote
                        formData={banknoteState}
                        setFormData={setBanknoteState}
                        onEvaluate={handleEvaluate}
                        result={evaluationResult}
                        loading={loading}
                    />

                    <GradeGoals currentBanknoteState={banknoteState} />
                </main>
            </div>
        </div>
    );
}

export default App;
