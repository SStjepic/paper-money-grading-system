import React, { useState } from "react";
import { banknoteApi } from "../services/api";
import {
    IBNSGrade,
    parseMissingInput,
    parseGradingRequirement
} from "../constants/enums";

export default function GradeGoals({ currentBanknoteState }) {
    const [selectedGrade, setSelectedGrade] = useState(IBNSGrade[0]);
    const [requirements, setRequirements] = useState([]);
    const [isAchievable, setIsAchievable] = useState(null);
    const [missingInputs, setMissingInputs] = useState([]);

    const getGradeCheckPayload = () => {
        return {
            banknote: currentBanknoteState,
            grade: selectedGrade
        };
    };

    const handleGetRequirements = async () => {
        try {
            const data =
                await banknoteApi.getRequirementsForGrade(selectedGrade);
            setRequirements(data);
        } catch (err) {
            console.error(err);
            alert("Error fetching grading requirements.");
        }
    };

    const handleCheckGoalStatus = async () => {
        try {
            const payload = getGradeCheckPayload();
            const achievable = await banknoteApi.checkAchievable(payload);
            setIsAchievable(achievable);

            const missing = await banknoteApi.getMissingInputs(payload);
            setMissingInputs(missing);
        } catch (err) {
            console.error(err);
            alert("Error checking target grade feasibility.");
        }
    };

    return (
        <div className="bg-white shadow-md rounded-lg p-6 border border-gray-200 mb-8">
            <h2 className="text-2xl font-bold text-gray-800 mb-2 border-b pb-2">
                Goal and Requirement Verification (Backward Chaining)
            </h2>
            <p className="text-xs text-gray-500 italic mb-4">
                *The system evaluates features based on the current form state
                above.
            </p>

            <div className="flex flex-col sm:flex-row sm:items-center gap-3 mb-6">
                <label className="font-semibold text-gray-700">
                    Select Target IBNS Grade:
                </label>
                <select
                    className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white cursor-pointer"
                    value={selectedGrade}
                    onChange={(e) => {
                        setSelectedGrade(e.target.value);
                        setRequirements([]);
                        setIsAchievable(null);
                        setMissingInputs([]);
                    }}
                >
                    {IBNSGrade.map((g) => (
                        <option key={g} value={g}>
                            {g}
                        </option>
                    ))}
                </select>
            </div>

            <div className="flex flex-wrap gap-3 mb-6">
                <button
                    onClick={handleGetRequirements}
                    className="px-4 py-2 bg-cyan-600 hover:bg-cyan-700 text-white font-semibold rounded-md transition duration-150 shadow-sm"
                >
                    Show Requirements for {selectedGrade}
                </button>
                <button
                    onClick={handleCheckGoalStatus}
                    className="px-4 py-2 bg-green-600 hover:bg-green-700 text-white font-semibold rounded-md transition duration-150 shadow-sm"
                >
                    Check Grade Feasibility
                </button>
            </div>

            {isAchievable !== null && (
                <div
                    className={`p-4 rounded-md border mb-4 ${
                        isAchievable
                            ? "bg-green-50 border-green-300 text-green-800"
                            : "bg-red-50 border-red-300 text-red-800"
                    }`}
                >
                    <strong className="font-bold">
                        Is this grade achievable?
                    </strong>
                    {isAchievable
                        ? " YES, current features satisfy this grading level."
                        : " NO, the banknote has defects blocking this grade."}
                </div>
            )}

            {missingInputs.length > 0 && (
                <div className="p-5 rounded-xl border border-amber-200 bg-amber-50/60 shadow-xs mb-6">
                    <div className="flex items-center gap-2 text-amber-800 mb-4">
                        <svg
                            className="w-5 h-5 shrink-0"
                            fill="none"
                            stroke="currentColor"
                            strokeWidth="2"
                            viewBox="0 0 24 24"
                        >
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"
                            />
                        </svg>
                        <h3 className="font-bold text-base tracking-tight">
                            Required conditions to achieve the target grade:
                        </h3>
                    </div>

                    <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
                        {missingInputs.map((rawInput, i) => {
                            const parsed = parseMissingInput(rawInput);
                            return (
                                <div
                                    key={i}
                                    className="flex flex-col p-3 bg-white border border-amber-100 rounded-lg shadow-2xs hover:border-amber-300 transition-colors"
                                >
                                    <span className="text-xs font-bold text-gray-400 uppercase tracking-wider">
                                        Feature
                                    </span>
                                    <span className="text-sm font-semibold text-gray-800 mb-1">
                                        {parsed.feature}
                                    </span>
                                    <div className="mt-1 flex items-center gap-1.5">
                                        <span className="text-xs text-amber-700 bg-amber-100 font-medium px-2 py-0.5 rounded-md border border-amber-200/60">
                                            Must be:{" "}
                                            <strong className="font-semibold">
                                                {parsed.value}
                                            </strong>
                                        </span>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </div>
            )}

            {requirements.length > 0 && (
                <div className="mt-8">
                    <h4 className="text-lg font-bold text-gray-800 mb-4 flex items-center gap-2">
                        <span className="w-2.5 h-5 bg-blue-600 rounded-full"></span>
                        Rule Structure and Conditions for {selectedGrade}:
                    </h4>

                    <div className="w-full overflow-x-auto rounded-xl border border-gray-200 shadow-md bg-white">
                        <table className="w-full table-fixed divide-y divide-gray-200 text-left text-sm">
                            <thead className="bg-gray-50 font-semibold text-gray-600 uppercase text-xs tracking-wider">
                                <tr>
                                    <th className="w-1/4 px-6 py-4">
                                        Type / Feature
                                    </th>
                                    <th className="w-1/4 px-6 py-4">
                                        Required Value
                                    </th>
                                    <th className="w-1/6 px-6 py-4 hidden md:table-cell">
                                        Level (Drools)
                                    </th>
                                    <th className="w-1/3 px-6 py-4">
                                        Rule Context (Explanation)
                                    </th>
                                </tr>
                            </thead>
                            <tbody className="divide-y divide-gray-200 bg-white">
                                {requirements.map((req, i) => {
                                    const parsed = parseGradingRequirement(
                                        req.requirement
                                    );

                                    let badgeStyle =
                                        "bg-gray-100 text-gray-700 border-gray-200";
                                    if (parsed.type === "INPUT")
                                        badgeStyle =
                                            "bg-emerald-50 text-emerald-700 border-emerald-200";
                                    if (parsed.type === "DERIVED")
                                        badgeStyle =
                                            "bg-blue-50 text-blue-700 border-blue-200";
                                    if (parsed.type === "LIMIT")
                                        badgeStyle =
                                            "bg-rose-50 text-rose-700 border-rose-200";
                                    if (
                                        parsed.type === "GROUP" ||
                                        parsed.type === "STATUS"
                                    )
                                        badgeStyle =
                                            "bg-purple-50 text-purple-700 border-purple-200";

                                    let displayLabel = parsed.label;
                                    if (
                                        typeof displayLabel === "string" &&
                                        displayLabel.startsWith("INPUT:")
                                    ) {
                                        displayLabel = displayLabel.replace(
                                            "INPUT:",
                                            ""
                                        );
                                        displayLabel =
                                            displayLabel
                                                .charAt(0)
                                                .toUpperCase() +
                                            displayLabel.slice(1);
                                    }

                                    return (
                                        <tr
                                            key={i}
                                            className="hover:bg-gray-50/80 transition-colors"
                                        >
                                            <td className="px-6 py-4">
                                                <div className="flex flex-col gap-1.5 items-start">
                                                    <span
                                                        className={`inline-flex items-center px-2 py-0.5 rounded-md text-[11px] font-bold border ${badgeStyle}`}
                                                    >
                                                        {parsed.type}
                                                    </span>
                                                    <span className="font-bold text-gray-900 text-sm block wrap-break-words max-w-full">
                                                        {displayLabel}
                                                    </span>
                                                </div>
                                            </td>
                                            <td className="px-6 py-4">
                                                {parsed.value ? (
                                                    <span className="inline-block bg-gray-50 border border-gray-200 text-gray-800 px-2.5 py-1 rounded-md text-xs font-mono font-medium wrap-break-words max-w-full">
                                                        {parsed.value}
                                                    </span>
                                                ) : (
                                                    <span className="text-gray-400 italic text-xs">
                                                        Default
                                                    </span>
                                                )}
                                            </td>
                                            <td className="px-6 py-4 hidden md:table-cell">
                                                <span className="inline-block text-xs font-mono font-semibold text-slate-500 bg-slate-50 px-2 py-0.5 rounded border border-slate-200">
                                                    {req.level}
                                                </span>
                                            </td>
                                            <td className="px-6 py-4 text-gray-600 text-sm leading-relaxed whitespace-normal wrap-break-words">
                                                {req.explanation}
                                            </td>
                                        </tr>
                                    );
                                })}
                            </tbody>
                        </table>
                    </div>
                </div>
            )}
        </div>
    );
}
