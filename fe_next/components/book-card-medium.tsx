export default function BookCardMedium({ ...props }) {
    const book = props.book;
    return (
        <div className="grid grid-cols-[120px_auto] gap-2">
            <div className="aspect-[1/1] bg-red-500 flex justify-center items-center">
                <img className="h-full"
                    alt={`Cover of ${book.title}`}
                    src={book.cover} />
            </div>

            <div className="bg-slate-300 flex flex-col">
                <h3 className="w-full line-clamp-2 text-md font-bold leading-[1.5] font-serif">
                    {book.title}
                </h3>
                <p className="italic text-sm truncate grow">
                    {book.authors.map((a: any) => a.name).join("; ")}
                </p>
                <button className="bg-slate-500 rounded-md border border-red-500">
                    Update
                </button>
            </div>
        </div>
    )
}
