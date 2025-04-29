import { BookOpen, Eye, List, Star } from "lucide-react"

export default async function MyLists({ className = "" }) {
    return <div className={className}>
        <h1 className="text-2xl mb-6 font-bold flex items-center gap-2">
            <List size={24} strokeWidth={2} />
            User Lists
        </h1>
        <div className="">
            <div className="text-black p-2 px-4 flex gap-4 border-b-1 border-gray-500/40">
                <Star size={18} strokeWidth={2} className="self-center" />
                <div className="flex flex-col">
                    <h2 className="text-md">Favorites</h2>
                    <p className="text-sm">My favorite books</p>
                </div>
            </div>
            <div className="p-2 px-4 flex gap-4 border-b-1 border-gray-500/40">
                <BookOpen size={18} strokeWidth={2} className="self-center" />
                <div className="flex flex-col">
                    <h2 className="text-md">DNF</h2>
                    <p className="text-sm ">Books I did not finish</p>
                </div>
            </div>

            <div className="p-2 px-4 flex gap-4">
                <Eye size={18} strokeWidth={2} className="self-center" />
                <div className="flex flex-col">
                    <h2 className="text-md">My Custom List</h2>
                </div>
            </div>
        </div>
    </div>
}
