import React from 'react';
import { InputFeatures, formatLabel } from '../constants/enums';

export default function EvaluateBanknote({
    formData,
    setFormData,
    onEvaluate,
    result,
    loading
}) {
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onEvaluate();
    };

    return (
        <div className='bg-white shadow-md rounded-lg p-6 border border-gray-200 mb-8'>
            <h2 className='text-2xl font-bold text-gray-800 mb-6 border-b pb-2'>
                Banknote Grading Form
            </h2>

            <form
                onSubmit={handleSubmit}
                className='grid grid-cols-1 md:grid-cols-2 gap-4'
            >
                <div className='flex flex-col col-span-1 md:col-span-2'>
                    <label className='mb-1 font-semibold text-sm text-gray-700'>
                        Banknote ID:
                    </label>
                    <input
                        className='p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50'
                        type='text'
                        name='id'
                        value={formData.id}
                        onChange={handleChange}
                    />
                </div>

                {Object.keys(InputFeatures).map((featureName) => (
                    <div key={featureName} className='flex flex-col'>
                        <label className='mb-1 font-semibold text-sm text-gray-700'>
                            {formatLabel(featureName)}:
                        </label>
                        <select
                            className='p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white cursor-pointer'
                            name={featureName}
                            value={formData[featureName]}
                            onChange={handleChange}
                        >
                            {InputFeatures[featureName].map((option) => (
                                <option key={option} value={option}>
                                    {option}
                                </option>
                            ))}
                        </select>
                    </div>
                ))}

                <button
                    type='submit'
                    className='col-span-1 md:col-span-2 mt-4 p-3 bg-blue-600 hover:bg-blue-700 text-white font-bold rounded-md transition duration-200 disabled:bg-blue-400'
                    disabled={loading}
                >
                    {loading
                        ? 'Calculating grade...'
                        : 'Run Evaluation (Forward Chaining)'}
                </button>
            </form>

            {result && (
                <div className='mt-8 pt-6 border-t-2 border-blue-500 bg-gray-50 p-4 rounded-md'>
                    <div className='flex items-center justify-between mb-4'>
                        <h3 className='text-xl font-bold text-gray-800'>
                            Final Grade:
                        </h3>
                        <span className='bg-green-600 text-white px-4 py-1.5 rounded-full font-bold text-lg shadow-sm'>
                            {result.finalGrade || 'Undetermined'}
                        </span>
                    </div>

                    <h4 className='text-md font-semibold text-gray-700 mb-2'>
                        Imperfections Report (Defects):
                    </h4>
                    {result.reportSummary && result.reportSummary.length > 0 ? (
                        <div className='overflow-x-auto rounded-lg border border-gray-200 shadow-sm bg-white'>
                            <table className='min-w-full divide-y divide-gray-200 text-left text-sm'>
                                <thead className='bg-gray-100 font-semibold text-gray-700 uppercase text-xs'>
                                    <tr>
                                        <th className='px-4 py-3'>Feature</th>
                                        <th className='px-4 py-3'>
                                            Detected Value
                                        </th>
                                        <th className='px-4 py-3'>
                                            Explanation / Penalty
                                        </th>
                                    </tr>
                                </thead>
                                <tbody className='divide-y divide-gray-200'>
                                    {result.reportSummary.map((defect, i) => (
                                        <tr
                                            key={i}
                                            className='hover:bg-gray-50 transition'
                                        >
                                            <td className='px-4 py-3 font-medium text-gray-900'>
                                                {formatLabel(
                                                    defect.attributeName
                                                )}
                                            </td>
                                            <td className='px-4 py-3 text-red-600 font-semibold'>
                                                {defect.detectedValue}
                                            </td>
                                            <td className='px-4 py-3 text-gray-600'>
                                                {defect.penaltyMessage}
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    ) : result.finalGrade === 'UNCIRCULATED' ? (
                        <div className='p-4 rounded-md border border-green-200 bg-green-50 text-green-800 font-medium shadow-2xs'>
                            <p>The banknote has no damage!</p>
                        </div>
                    ) : (
                        <div className='p-4 rounded-md border border-amber-200 bg-amber-50 text-amber-800 font-medium shadow-2xs'>
                            <p>No specific defects found, but current data is insufficient to determine the exact grade. Please verify all inputs or use the Goal Verification tool below.</p>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
}